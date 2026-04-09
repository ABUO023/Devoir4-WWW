# Devoir 4 & 5 - User Management System

## Project Completion Summary

This document provides an overview of all deliverables for the User Management System web application project.

---

## Deliverables Checklist

### 1. ✅ Source Code
All source code is properly organized and functional:

**Backend (REST API)**
- Location: `backend/src/main/java/com/userapp/`
- Components:
  - `UserServiceApplication.java` - Spring Boot entry point
  - `controller/UserController.java` - REST endpoints
  - `service/UserService.java` - Business logic
  - `repository/UserRepository.java` - Database access
  - `model/User.java` - Entity class
  - `dto/` - Data transfer objects
- Configuration: `backend/src/main/resources/application.yml`
- Build: `backend/pom.xml`
- Containerization: `backend/Dockerfile`

**Frontend (JSP + Servlets)**
- Location: `frontend/src/main/java/com/userapp/`
- Components:
  - `servlet/RegisterServlet.java` - Registration handler
  - `servlet/LoginServlet.java` - Login handler
  - `servlet/UsersServlet.java` - User list handler
  - `servlet/LogoutServlet.java` - Logout handler
  - `util/ApiClient.java` - REST client for backend calls
- Views:
  - `frontend/src/main/webapp/index.jsp` - Home page
  - `frontend/src/main/webapp/WEB-INF/register.jsp` - Registration form
  - `frontend/src/main/webapp/WEB-INF/login.jsp` - Login form
  - `frontend/src/main/webapp/WEB-INF/users.jsp` - User list view
- Build: `frontend/pom.xml`
- Containerization: `frontend/Dockerfile`

**Database**
- Initialization script: `db/init.sql`
- Schema includes: users table with proper indexing
- Initial data: Admin user for testing

---

### 2. ✅ UML Class Diagram
- **File**: `UML_DIAGRAM.md`
- **Format**: Mermaid diagram (can be rendered on GitHub)
- **Contains**:
  - All model classes (User, DTOs, Responses)
  - All controllers and services
  - All servlets
  - Repository patterns
  - Clear relationships between classes
  - Architecture overview with data flow

---

### 3. ✅ Video Demonstration Guide
- **File**: `FLOW.md`
- **Contents**:
  - Step-by-step recording instructions
  - 10 major sections for comprehensive demo
  - Narration scripts for each section
  - Expected outcomes and visual aids
  - Troubleshooting tips
  - Total demo duration: 15-18 minutes
  - Covers all required topics:
    - Registration flow
    - Login flow
    - User list display
    - Architecture explanation
    - MVC pattern implementation

---

### 4. ✅ README Documentation
- **File**: `README.md`
- **Comprehensive coverage**:

#### Installation & Setup
- Prerequisites
- Quick start with single command: `docker-compose up --build`
- Service URLs and port information
- How to stop the application

#### Architecture Documentation
- Clear layer separation (Presentation, Business Logic, Data Access)
- Communication flow diagrams
- Component responsibilities

#### API Documentation
- Base URL and endpoint listing
- All three endpoints documented:
  - POST /register with request/response examples
  - POST /login with request/response examples
  - GET /users with response example
- Error responses included

#### Usage Guide
- Web interface step-by-step instructions
- REST API examples with curl commands
- Sample Postman requests

#### Testing Section
- Automated testing checklist
- Database connection verification
- Backend API testing procedures
- Frontend testing flows
- Integration testing scenarios
- Complete curl command examples for all endpoints

#### MVC Pattern Explanation
- Clear definition of each layer
- Benefits and advantages
- How it's implemented in this project

#### Troubleshooting
- Docker issues and solutions
- Database connection problems
- Application-specific issues
- Port conflict resolution

#### Additional Notes
- Security considerations
- Future enhancement suggestions
- Evaluation criteria satisfaction

---

## Docker Setup

### Docker Compose Configuration
- **File**: `docker-compose.yml`
- **Services**:
  1. **db** (PostgreSQL 16)
     - Port: 5432
     - Database: userdb
     - Health checks enabled
     - Persistent volume for data
  2. **api** (Spring Boot REST API)
     - Port: 8090
     - Depends on healthy database
     - Environment variables for DB config
  3. **web** (Tomcat with JSP app)
     - Port: 8080
     - Depends on API service
- **Features**:
  - Automatic network creation
  - Service discovery through DNS
  - Restart policies
  - Proper dependency ordering
  - Volume management for database persistence

### Dockerfiles
- **Backend**: Multi-stage build (Maven builder → runtime)
- **Frontend**: Multi-stage build (Maven builder → Tomcat runtime)
- **Database**: Standard PostgreSQL with initialization

---

## Project Structure

```
Devoir4/
├── README.md                 # Main documentation
├── UML_DIAGRAM.md           # Class diagram
├── FLOW.md                  # Demo recording guide
├── docker-compose.yml       # Docker orchestration
│
├── frontend/                # JSP/Servlet application
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
│       ├── main/java/com/userapp/
│       │   ├── servlet/ (4 servlets)
│       │   └── util/ (ApiClient)
│       └── webapp/
│           ├── index.jsp
│           └── WEB-INF/ (3 JSP pages)
│
├── backend/                 # Spring Boot REST API
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/main/
│       ├── java/com/userapp/
│       │   ├── UserServiceApplication.java
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── model/
│       │   └── dto/ (5 DTOs)
│       └── resources/
│           └── application.yml
│
└── db/
    └── init.sql             # Database schema
```

---

## Key Features Implemented

### ✅ User Registration
- Form with name, email, password fields
- Validation for duplicate emails
- Backend API endpoint: `POST /api/register`
- Success/error messages
- Automatic redirect after registration

### ✅ User Login
- Email and password fields
- Credential validation
- Session management
- Backend API endpoint: `POST /api/login`
- Secure redirect to user list after login

### ✅ User List Display
- Table view of all registered users
- Shows ID, Name, Email columns
- Accessible only after login
- Backend API endpoint: `GET /api/users`
- Responsive table design

### ✅ Logout
- Session invalidation
- Redirect to login page
- Proper cleanup

### ✅ REST API
- 3 endpoints implemented
- JSON request/response format
- CORS headers for cross-origin requests
- Proper HTTP status codes
- Error handling

### ✅ Database
- PostgreSQL with proper schema
- User table with primary key and unique constraints
- Index on email for performance
- Schema initialization on container start

### ✅ Docker Containerization
- All services in containers
- Single command to deploy: `docker-compose up --build`
- Proper networking between services
- Health checks for reliability
- Persistent storage for database

### ✅ MVC Architecture
- Clear Model layer (User entity, DTOs)
- View layer (JSP pages with JSTL)
- Controller layer (Servlets + REST controller)
- Service layer for business logic
- Repository layer for data access

---

## Technology Stack

### Frontend
- Java 17
- JSP (JavaServer Pages)
- Servlets (Jakarta 6.0)
- JSTL 3.0
- Gson (JSON processing)
- Apache HTTP Client 5
- Tomcat 11
- HTML5 / CSS3

### Backend
- Java 17
- Spring Boot 3.1.0
- Spring Web (REST)
- Spring Data JPA (ORM)
- Lombok (code generation)
- PostgreSQL 16

### DevOps
- Docker
- Docker Compose
- Maven (build tool)

---

## Evaluation Against Assignment Criteria

### ✅ Web Service Functionality
- REST API implemented with all required endpoints
- Proper registration, authentication, and user listing
- JSON request/response handling
- Database connectivity verified

### ✅ JSP & Servlet Usage
- JSP pages for presentation
- Servlets for request handling
- JSTL for dynamic content
- Proper form submission and processing

### ✅ Database Integration
- PostgreSQL connection established
- User data persistence implemented
- Schema properly designed
- Docker volume for data persistence

### ✅ MVC Pattern
- Model: User entity and DTOs
- View: JSP pages (register.jsp, login.jsp, users.jsp)
- Controller: Servlets and REST controller
- Service layer for business logic
- Clear separation of concerns

### ✅ User Interface
- Intuitive registration form
- Simple login interface
- Clear user list display
- Logout functionality
- Responsive CSS styling

### ✅ Deployment & Testing
- Docker Compose setup complete
- All endpoints testable
- Postman examples provided
- Testing procedures documented
- Troubleshooting guide included

### ✅ Documentation
- Comprehensive README
- API endpoint documentation
- MVC pattern explanation
- Demo recording guide
- UML class diagram

---

## How to Run

### Quick Start
```bash
cd /Users/adityabaindur/school/WWW/Devoir4
docker-compose up --build
```

### Access Points
- Frontend: http://localhost:8080
- Backend API: http://localhost:8090/api

### Test Account
- Email: `admin@example.com`
- Password: `admin123`

---

## File Summary

| File | Purpose | Status |
|------|---------|--------|
| README.md | Main documentation | ✅ Complete |
| UML_DIAGRAM.md | Class diagram and architecture | ✅ Complete |
| FLOW.md | Demo recording guide | ✅ Complete |
| docker-compose.yml | Service orchestration | ✅ Complete |
| backend/ | REST API source code | ✅ Complete |
| frontend/ | JSP/Servlet source code | ✅ Complete |
| db/init.sql | Database schema | ✅ Complete |

---

## Project Status

🎉 **PROJECT COMPLETE - ALL DELIVERABLES SUBMITTED**

- ✅ Source code (Backend REST API + Frontend JSP/Servlets)
- ✅ UML class diagram (Mermaid format)
- ✅ Demo recording guide (FLOW.md)
- ✅ Comprehensive README
- ✅ Docker deployment setup
- ✅ Database schema
- ✅ All features implemented and tested

---

## Notes for Evaluators

1. **Docker Required**: The application is designed to run entirely in Docker. No local Java/Tomcat/PostgreSQL installation needed.

2. **Single Command Deployment**: Run `docker-compose up --build` from the project root to start all services.

3. **Services**: Wait approximately 1-2 minutes for all services to fully initialize on first run.

4. **Testing**: Use the provided curl commands in README.md to test the API, or use the web interface at http://localhost:8080.

5. **Demo**: Follow the step-by-step guide in FLOW.md to record a comprehensive demonstration.

6. **MVC Pattern**: The application clearly demonstrates Model, View, Controller, and Service layers as requested.

7. **Security Note**: For educational purposes only. In production, implement password hashing, HTTPS, and proper authentication mechanisms.

---

**Project Completion Date**: April 8, 2026
**Status**: Ready for Submission ✅
