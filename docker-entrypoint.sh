#!/bin/bash
set -e

echo "=== Starting User Management System (All-in-One Container) ==="

# Initialize PostgreSQL data directory if not already done
if [ ! -f /var/lib/postgresql/16/data/PG_VERSION ]; then
    echo "Initializing PostgreSQL database..."
    sudo -u postgres /usr/lib/postgresql/16/bin/initdb -D /var/lib/postgresql/16/data
fi

# Start PostgreSQL in the background
echo "Starting PostgreSQL..."
sudo service postgresql start
sleep 3

# Initialize database schema if needed
if ! sudo -u postgres psql -lqt | cut -d \| -f 1 | grep -qw userdb; then
    echo "Creating userdb database..."
    sudo -u postgres psql -c "CREATE DATABASE userdb;"
fi

# Check if users table exists, if not run init script
if ! sudo -u postgres psql -d userdb -lqt | grep -q users; then
    echo "Initializing database schema..."
    sudo -u postgres psql -d userdb -f /docker-entrypoint-initdb.d/init.sql
fi

# Start Spring Boot API in the background
echo "Starting Spring Boot API..."
java -jar /app/api.jar > /tmp/api.log 2>&1 &
API_PID=$!

# Wait for API to be ready
echo "Waiting for API to be ready..."
for i in {1..60}; do
    if curl -s http://localhost:8090/api/users >/dev/null 2>&1; then
        echo "API is ready!"
        break
    fi
    if [ $i -eq 60 ]; then
        echo "API failed to start. Check /tmp/api.log"
        tail -50 /tmp/api.log
    fi
    sleep 1
done

# Start Tomcat in foreground
echo "Starting Tomcat..."
exec /usr/sbin/catalina.sh run
