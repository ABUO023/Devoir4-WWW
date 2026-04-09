# UML Class Diagram - User Management System

```mermaid
classDiagram
    class User {
        -Long id
        -String name
        -String email
        -String password
        +getId() Long
        +getName() String
        +setName(name) void
        +getEmail() String
        +setEmail(email) void
        +getPassword() String
        +setPassword(password) void
    }

    class UserRepository {
        +save(user) User
        +findAll() List<User>
        +findByEmail(email) User
        +findById(id) User
    }

    class UserService {
        -UserRepository userRepository
        +register(name, email, password) User
        +authenticate(email, password) User
        +getAllUsers() List<UserDTO>
    }

    class UserController {
        -UserService userService
        +register(request) ResponseEntity
        +login(request) ResponseEntity
        +getUsers() ResponseEntity
    }

    class RegisterRequest {
        -String name
        -String email
        -String password
    }

    class LoginRequest {
        -String email
        -String password
    }

    class UserDTO {
        -Long id
        -String name
        -String email
    }

    class LoginResponse {
        -boolean success
        -String message
        -UserDTO user
    }

    class ApiResponse {
        -boolean success
        -String message
        -Object data
    }

    class RegisterServlet {
        +doGet(request, response)
        +doPost(request, response)
    }

    class LoginServlet {
        +doGet(request, response)
        +doPost(request, response)
    }

    class UsersServlet {
        +doGet(request, response)
    }

    class LogoutServlet {
        +doGet(request, response)
    }

    class ApiClient {
        +post(endpoint, jsonBody) String
        +get(endpoint) String
    }

    UserService --> UserRepository
    UserController --> UserService
    UserController --> RegisterRequest
    UserController --> LoginRequest
    UserController --> UserDTO
    UserController --> ApiResponse
    UserController --> LoginResponse
    RegisterServlet --> ApiClient
    LoginServlet --> ApiClient
    UsersServlet --> ApiClient
    LogoutServlet --|> HttpServlet
    LoginResponse --> UserDTO
```

## Architecture Overview

### Layers

1. **Database Layer**: PostgreSQL
   - Stores user data (id, name, email, password)

2. **Backend Layer** (REST API - Port 8090):
   - `UserController`: REST endpoints
   - `UserService`: Business logic
   - `UserRepository`: Database access
   - `User`: Entity model
   - DTOs: Data transfer objects

3. **Frontend Layer** (JSP - Port 8080):
   - `RegisterServlet`: Handles user registration
   - `LoginServlet`: Handles user login
   - `UsersServlet`: Displays list of users
   - `LogoutServlet`: Handles logout
   - `ApiClient`: Communicates with backend API
   - JSP Pages: User interface

### Data Flow

1. **Registration Flow**:
   - User fills form → RegisterServlet → ApiClient → Backend API → UserService → Database

2. **Login Flow**:
   - User fills form → LoginServlet → ApiClient → Backend API → UserService → Database

3. **View Users Flow**:
   - User navigates → UsersServlet → ApiClient → Backend API → UserService → Database

### Communication

- Frontend and Backend communicate via REST API (HTTP)
- Frontend makes JSON requests to Backend API endpoints
- Backend returns JSON responses
