#!/bin/bash

# All-in-One Application Startup Script
# This script initializes the database, starts the API, and starts Tomcat

set -e

echo "=== Starting User Management System (All-in-One Container) ==="

# Start PostgreSQL service
echo "Starting PostgreSQL..."
service postgresql start
sleep 2

# Wait for PostgreSQL to be ready
echo "Waiting for PostgreSQL to be ready..."
for i in {1..30}; do
    if sudo -u postgres pg_isready -h localhost >/dev/null 2>&1; then
        echo "PostgreSQL is ready!"
        break
    fi
    echo "Attempt $i: PostgreSQL not ready, waiting..."
    sleep 1
done

# Initialize database
echo "Initializing database..."
sudo -u postgres psql -c "CREATE DATABASE userdb;" 2>/dev/null || echo "Database already exists"
sudo -u postgres psql -d userdb -f /docker-entrypoint-initdb.d/init.sql 2>/dev/null || echo "Schema already initialized"

# Start API server
echo "Starting Spring Boot API server..."
java -jar /opt/api/app.jar &
API_PID=$!

# Wait for API to be ready
echo "Waiting for API to be ready..."
sleep 10
for i in {1..60}; do
    if curl -s http://localhost:8090/api/users >/dev/null 2>&1; then
        echo "API is ready!"
        break
    fi
    echo "Attempt $i: API not ready yet, waiting..."
    sleep 1
done

# Start Tomcat in foreground
echo "Starting Tomcat..."
exec /usr/share/tomcat9/bin/catalina.sh run
