# User Management System - Web Application

A full-stack Java web application demonstrating JSP/Servlets frontend with REST API backend, built with Spring Boot and PostgreSQL, containerized with Docker.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Tech Stack](#tech-stack)
3. [Project Structure](#project-structure)
4. [Quick Start](#quick-start)
5. [Architecture](#architecture)
6. [API Documentation](#api-documentation)
7. [MVC Pattern](#mvc-pattern)

---

## Project Overview

### Hosted Version of this project : [https://d4.adityabaindur.dev/](https://d4.adityabaindur.dev/)

### Demo

https://github.com/user-attachments/assets/cec1bcc4-b1da-4d4a-b855-21dfd53336e5

[Demo Video Link](./assets/UMS.mp4)

This is a **User Management System** that allows users to:

- Register with name, email, and password
- Log in with email and password
- View a list of all registered users (after login)

The application follows the **MVC (Model-View-Controller)** architectural pattern with clear separation of concerns:

- **Frontend**: JSP pages + Servlets for presentation layer
- **Backend**: REST API for business logic
- **Database**: PostgreSQL for data persistence

This whole project runs on Docker due to an issue with my Tomcat installation. Check out [Quick Start](#quick-start).

### UML

The Interactive UML diagram with more explanations can be found in [UML_DIAGRAM.md](UML_DIAGRAM.md) (It was build using Mermaid.js)

![UML](assets/uml.svg)

---

## Tech Stack

### Frontend

- **Java** 17
- **JSP** (JavaServer Pages)
- **Servlets** (Jakarta Servlet API)
- **JSTL** (JavaServer Pages Standard Tag Library)
- **HTML/CSS**
- **Apache HTTP Client** (for REST calls)
- **Gson** (JSON processing)
- **Tomcat** 11

### Backend

- **Java** 17
- **Spring Boot** 3.1.0
- **Spring Web** (REST endpoints)
- **Spring Data JPA** (ORM)
- **PostgreSQL** Driver
- **Lombok** (code generation)

### Database

- **PostgreSQL** 16

### DevOps

- **Docker** & **Docker Compose**

---

## Project Structure

```
Devoir4/
├── docker-compose.yml          # Docker services orchestration
├── README.md                   # This file
├── UML_DIAGRAM.md             # UML class diagram
├── FLOW.md                    # Demo recording guide
│
├── frontend/                  # JSP/Servlet Application
│   ├── pom.xml               # Maven configuration
│   ├── Dockerfile            # Frontend container image
│   └── src/
│       ├── main/java/com/userapp/
│       │   ├── util/
│       │   │   └── ApiClient.java         # REST client
│       │   └── servlet/
│       │       ├── RegisterServlet.java   # Registration handler
│       │       ├── LoginServlet.java      # Login handler
│       │       ├── UsersServlet.java      # User list handler
│       │       └── LogoutServlet.java     # Logout handler
│       └── webapp/
│           ├── index.jsp                 # Home page
│           └── WEB-INF/
│               ├── register.jsp          # Registration form
│               ├── login.jsp             # Login form
│               └── users.jsp             # User list page
│
├── backend/                   # Spring Boot REST API
│   ├── pom.xml               # Maven configuration
│   ├── Dockerfile            # Backend container image
│   └── src/main/
│       ├── java/com/userapp/
│       │   ├── UserServiceApplication.java   # Main Spring Boot app
│       │   ├── controller/
│       │   │   └── UserController.java       # REST endpoints
│       │   ├── service/
│       │   │   └── UserService.java          # Business logic
│       │   ├── repository/
│       │   │   └── UserRepository.java       # Database access
│       │   ├── model/
│       │   │   └── User.java                 # Entity
│       │   └── dto/
│       │       ├── UserDTO.java
│       │       ├── RegisterRequest.java
│       │       ├── LoginRequest.java
│       │       ├── LoginResponse.java
│       │       └── ApiResponse.java
│       └── resources/
│           └── application.yml          # Spring configuration
│
└── db/
    └── init.sql               # PostgreSQL initialization script
```

---

## Quick Start

### Prerequisites

- Docker & Docker Compose installed
- (Optional) Git for cloning

### Installation & Running

**Step 1: Clone or navigate to the project directory**

```bash
git clone https://github.com/ABUO023/Devoir4-WWW.git 
cd Devoir4-WWW
```

**Step 2: Start all services with Docker Compose**

```bash
docker-compose up --build
```

**What this does:**

1. Builds and starts PostgreSQL database (Port 5432)
2. Builds and starts Spring Boot REST API (Port 8090)
3. Builds and starts Tomcat with JSP application (Port 8080)
4. Initializes database schema from `db/init.sql`

**Step 3: Access the application**

- Frontend: http://localhost:8080
- Backend API: http://localhost:8090/api

**Expected Output:**

```
✓ PostgreSQL running on port 5432
✓ Backend API running on port 8090
✓ Frontend web app running on port 8080
✓ All services healthy
```

### Stopping the Application

```bash
docker-compose down
```

---

## Architecture

### Layers

#### 1. Presentation Layer (Frontend)

- **Technology**: JSP + Servlets + HTML/CSS
- **Port**: 8080
- **Components**:
  - `RegisterServlet`: Handles POST requests for user registration
  - `LoginServlet`: Handles POST requests for user login
  - `UsersServlet`: Handles GET requests to display user list
  - `LogoutServlet`: Handles logout
  - `ApiClient`: Utility class for making HTTP requests to backend

#### 2. Business Logic Layer (Backend)

- **Technology**: Spring Boot REST API
- **Port**: 8090
- **Components**:
  - `UserController`: REST endpoints (registration, login, list users)
  - `UserService`: Business logic for user operations
  - `UserRepository`: JPA repository for database access

#### 3. Data Access Layer

- **Technology**: PostgreSQL + Spring Data JPA
- **Database Name**: `userdb`
- **Tables**: `users`

### Communication Flow

```
Client (Browser) 
    ↓
JSP Forms & Servlets (Port 8080)
    ↓
ApiClient (HTTP Requests)
    ↓
REST API Endpoints (Port 8090)
    ↓
Spring Boot Services
    ↓
JPA Repository
    ↓
PostgreSQL Database
```

---

## API Documentation

### Base URL (Add bindings to docker compose for local dev)

```
http://localhost:8090/api
```

### Endpoints

#### 1. Register User

**Endpoint**: `POST /api/register`

**Request Body**:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response (Success - 200)**:

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Response (Error - 400)**:

```json
{
  "success": false,
  "message": "User with this email already exists",
  "data": null
}
```

---

#### 2. Login User

**Endpoint**: `POST /api/login`

**Request Body**:

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

**Response (Success - 200)**:

```json
{
  "success": true,
  "message": "Login successful",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Response (Error - 400)**:

```json
{
  "success": false,
  "message": "Invalid password",
  "user": null
}
```

---

#### 3. Get All Users

**Endpoint**: `GET /api/users`

**Response (Success - 200)**:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane@example.com"
  }
]
```

---

## Usage Guide

### Via Web Interface (JSP Frontend)

#### 1. Register a New User

1. Navigate to http://localhost:8080
2. Click "Register"
3. Fill in Name, Email, and Password
4. Click "Register" button
5. Success: Redirected to login page with confirmation message
6. Error: Error message displayed on the form

#### 2. Login

1. Navigate to http://localhost:8080/login
2. Enter Email and Password
3. Click "Login" button
4. Success: Redirected to users list page
5. Error: Error message displayed on the form

#### 3. View Registered Users

1. After successful login, you'll see the user list
2. Displays ID, Name, and Email of all registered users
3. Click "Logout" to return to login page

#### 4. Logout

1. Click "Logout" button on the users page
2. Session invalidated and redirected to login page

---

### Via REST API (Using Postman or curl)

#### 1. Register a User

```bash
curl -X POST http://localhost:8090/api/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "password": "test123"
  }'
```

#### 2. Login

```bash
curl -X POST http://localhost:8090/api/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "test123"
  }'
```

#### 3. Get All Users

```bash
curl -X GET http://localhost:8090/api/users
```

## MVC Pattern

### Model

- **Entity**: `User` class with properties (id, name, email, password)
- **Repository**: `UserRepository` provides database access
- **DTOs**: `RegisterRequest`, `LoginRequest`, `UserDTO`, `LoginResponse` for data transfer

### View

- **JSP Pages**:
  - `register.jsp`: User registration form
  - `login.jsp`: User login form
  - `users.jsp`: Displays list of users
  - `index.jsp`: Home page
- **HTML/CSS**: Styled with gradient backgrounds and modern UI

### Controller

- **Backend Controllers**: `UserController` REST endpoints
- **Frontend Servlets**: `RegisterServlet`, `LoginServlet`, `UsersServlet`, `LogoutServlet`

### Service Layer

- **UserService**: Encapsulates business logic for user operations
- **ApiClient**: Utility for frontend-to-backend communication

**Benefits of MVC**:

- Separation of concerns
- Easy to test individual components
- Scalable and maintainable code
- Clear responsibility boundaries

---

## Troubleshooting

### Docker Issues

**Issue**: Ports already in use

```bash
# Solution: Check what's using the ports
lsof -i :8080
lsof -i :8090
lsof -i :5432

# Kill processes if needed
kill -9 <PID>

# Or use different ports in docker-compose.yml
```

**Issue**: Docker daemon not running

```bash
# Solution: Start Docker Desktop or Docker daemon
# macOS: open /Applications/Docker.app
# Linux: sudo systemctl start docker
```

**Issue**: Container exits immediately

```bash
# View logs
docker-compose logs -f api
docker-compose logs -f web
docker-compose logs -f db

# Rebuild
docker-compose down
docker-compose up --build
```

### Database Issues

**Issue**: "Cannot connect to database"

```bash
# Check if db container is running
docker ps | grep postgres

# Check database logs
docker-compose logs db

# Solution: Ensure init.sql is in db/ directory
```

**Issue**: "User already exists" error

```bash
# Clear database
docker-compose down -v

# Restart with fresh database
docker-compose up --build
```

### Application Issues

**Issue**: Frontend cannot reach backend API

```bash
# Check if backend is running
curl http://localhost:8090/api/users

# Check network connectivity between containers
docker exec userapp-web curl http://api:8090/api/users

# Verify api service name in docker-compose.yml
```

**Issue**: Session lost after redirect

```bash
# Ensure cookies are properly set
# Check browser console for cookie-related messages
```

---

Built with <3 @ UOttawa
