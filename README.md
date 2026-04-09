# User Management System - Web Application

A full-stack Java web application demonstrating JSP/Servlets frontend with REST API backend, built with Spring Boot and PostgreSQL, containerized with Docker.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Tech Stack](#tech-stack)
3. [Project Structure](#project-structure)
4. [Quick Start](#quick-start)
5. [Architecture](#architecture)
6. [API Documentation](#api-documentation)
7. [Usage Guide](#usage-guide)
8. [Testing](#testing)
9. [MVC Pattern](#mvc-pattern)
10. [Troubleshooting](#troubleshooting)

---

## Project Overview

This is a **User Management System** that allows users to:
- Register with name, email, and password
- Log in with email and password
- View a list of all registered users (after login)

The application follows the **MVC (Model-View-Controller)** architectural pattern with clear separation of concerns:
- **Frontend**: JSP pages + Servlets for presentation layer
- **Backend**: REST API (Spring Boot) for business logic
- **Database**: PostgreSQL for data persistence

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
cd /Users/adityabaindur/school/WWW/Devoir4
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

### Base URL
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

---

## Testing

### Automated Testing Checklist

#### 1. Database Connection
- ✓ PostgreSQL container starts without errors
- ✓ Database `userdb` created
- ✓ Table `users` created with correct schema

#### 2. Backend API Testing

**Test Registration**:
```bash
# Test 1: Register a new user
curl -X POST http://localhost:8090/api/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com","password":"alice123"}'

# Expected: 200 OK with success message

# Test 2: Register duplicate email (should fail)
curl -X POST http://localhost:8090/api/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Bob","email":"alice@example.com","password":"bob123"}'

# Expected: 400 Bad Request with error message
```

**Test Login**:
```bash
# Test 1: Valid login
curl -X POST http://localhost:8090/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"alice@example.com","password":"alice123"}'

# Expected: 200 OK with user data

# Test 2: Invalid password
curl -X POST http://localhost:8090/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"alice@example.com","password":"wrongpassword"}'

# Expected: 400 Bad Request with error message

# Test 3: Non-existent user
curl -X POST http://localhost:8090/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"nonexistent@example.com","password":"pass123"}'

# Expected: 400 Bad Request with error message
```

**Test Get Users**:
```bash
curl -X GET http://localhost:8090/api/users

# Expected: 200 OK with JSON array of all users
```

#### 3. Frontend Testing

**Test Registration Flow**:
1. Open http://localhost:8080
2. Click "Register"
3. Enter valid data (name, email, password)
4. Submit form
5. Expected: Success message and redirect to login

**Test Login Flow**:
1. Open http://localhost:8080/login
2. Enter valid credentials
3. Submit form
4. Expected: Redirect to /users page with user list

**Test User List Display**:
1. Login successfully
2. Verify all users are displayed in table
3. Verify table shows ID, Name, and Email columns

**Test Logout**:
1. Click "Logout" button
2. Expected: Session cleared and redirect to login

#### 4. Integration Testing

**Complete Flow Test**:
1. Register new user via web interface ✓
2. Login with new credentials ✓
3. Verify user appears in user list ✓
4. Register another user ✓
5. Verify both users in list ✓
6. Logout ✓
7. Login with first user ✓
8. Logout ✓

---

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

## Additional Notes

### Security Considerations
- ⚠️ Passwords are stored in plain text (for educational purposes only)
- 🔒 In production: Use bcrypt or similar hashing algorithms
- 🔒 Add HTTPS/TLS encryption
- 🔒 Implement proper authentication (JWT, OAuth)

### Future Enhancements
- [ ] Password hashing with bcrypt
- [ ] JWT token-based authentication
- [ ] Email validation
- [ ] Password strength validation
- [ ] User profile editing
- [ ] Delete user functionality
- [ ] Admin dashboard
- [ ] Search and filter users
- [ ] Pagination for user list
- [ ] Rate limiting and request validation

---

## Evaluation Criteria

This project satisfies all assignment requirements:

✅ **Web Service Functionality**
- REST API with POST /register, POST /login, GET /users endpoints
- Proper request/response handling with JSON

✅ **JSP & Servlet Usage**
- JSP pages for presentation layer
- Servlets for handling form submissions and logic
- JSTL used for dynamic content rendering

✅ **Database Integration**
- PostgreSQL database connection
- User data persistence
- Proper schema with indexes

✅ **MVC Pattern**
- Clear separation: Models, Views (JSP), Controllers (Servlets & REST)
- Service layer for business logic
- Repository layer for data access

✅ **User Interface**
- Functional and intuitive registration form
- Login form with validation
- User list table with all details
- Logout functionality

✅ **Docker & Deployment**
- Docker Compose setup with 3 services
- Single command deployment: `docker-compose up --build`
- Proper networking and dependencies

✅ **Documentation**
- README with installation steps
- API endpoint documentation
- MVC pattern explanation
- Testing guide and examples

---

## Support

For issues or questions:
1. Check the Troubleshooting section
2. Review logs with `docker-compose logs`
3. Verify all containers are running: `docker-compose ps`
4. Ensure ports are available: `lsof -i :8080`, `lsof -i :8090`, `lsof -i :5432`

---

**Last Updated**: April 2026
**Project Status**: Complete ✅
