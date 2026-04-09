# All-in-One Container with PostgreSQL, Spring Boot API, and Tomcat
# This Dockerfile builds a single container that runs all three services
# 
# Build: docker build -t userapp:latest -f Dockerfile .
# Run:   docker run -p 8080:8080 userapp:latest

# ============================================
# Stage 1: Build Backend API JAR
# ============================================
FROM maven:3.9.4-eclipse-temurin-17 AS api-builder

WORKDIR /build/backend
COPY backend/pom.xml .
COPY backend/src/ src/

RUN mvn clean package -DskipTests && \
    mv target/user-service-1.0.0.jar /tmp/app.jar

# ============================================
# Stage 2: Build Frontend WAR
# ============================================
FROM maven:3.9.4-eclipse-temurin-17 AS web-builder

WORKDIR /build/frontend
COPY frontend/pom.xml .
COPY frontend/src/ src/

RUN mvn clean package -DskipTests && \
    mv target/user-web.war /tmp/ROOT.war

# ============================================
# Stage 3: Runtime Container
# ============================================
FROM ubuntu:22.04

# Install required packages
RUN apt-get update && apt-get install -y \
    postgresql \
    postgresql-contrib \
    openjdk-17-jre-headless \
    tomcat9 \
    curl \
    sudo \
    vim \
    && rm -rf /var/lib/apt/lists/*

# Create initialization directories
RUN mkdir -p /docker-entrypoint-initdb.d && \
    mkdir -p /opt/api && \
    mkdir -p /var/log/app

# Copy database initialization script
COPY db/init.sql /docker-entrypoint-initdb.d/

# Copy startup script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Copy built artifacts
COPY --from=api-builder /tmp/app.jar /opt/api/app.jar
COPY --from=web-builder /tmp/ROOT.war /var/lib/tomcat9/webapps/ROOT.war

# Ensure Tomcat webapps directory is clean
RUN rm -rf /var/lib/tomcat9/webapps/ROOT && \
    mkdir -p /var/lib/tomcat9/webapps

# Create symlink for Tomcat (some scripts reference /usr/local/tomcat)
RUN ln -sf /var/lib/tomcat9 /usr/local/tomcat

# Expose port 8080 (Tomcat)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080 || exit 1

# Start all services
ENTRYPOINT ["/entrypoint.sh"]
