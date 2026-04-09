## 🧠 Project Overview

You are building a **Java web application** using:

* **JSP + Servlets** → frontend (presentation layer)
* **Web Services (REST preferred, JAX-RS)** → backend logic
* **Database (PostgreSQL or MySQL)** → persistence
* **Docker ONLY** → no local runtime dependencies

The application is a **User Management System** with:

* User registration
* User login
* Display list of registered users

---

## Critical Constraints

* DO NOT rely on local installations (Java, Tomcat, DB, etc.)
* EVERYTHING must run via **Docker**
* Provide a **docker-compose setup**
* The app must be runnable with a single command:

  ```bash
  docker-compose up --build
  ```

---

## Architecture Requirements

Follow a clean **MVC structure**:

### Model

* User entity (id, name, email, password)

### View (JSP)

* Registration page
* Login page
* User list page

### Controller (Servlets)

* Handle form submissions
* Call backend Web Service

### Backend (Web Service)

* REST API (JAX-RS preferred)
* Handles:

  * Register user
  * Authenticate user
  * Get all users

---

## 🔧 Features to Implement

### 1. Web Service (Backend)

Build a REST API with endpoints:

* `POST /register`

  * Input: name, email, password
  * Store user in DB

* `POST /login`

  * Validate credentials
  * Return success/failure

* `GET /users`

  * Return list of users (JSON)

### Requirements:

* Use Java (JAX-RS or Spring Boot acceptable if simpler)
* Connect to PostgreSQL/MySQL
* Use proper layering (controller/service/repository)

---

### 2. JSP + Servlets (Frontend)

Create:

* `/register.jsp`
* `/login.jsp`
* `/users.jsp`

### Requirements:

* Use **JSTL**
* Forms submit to Servlets
* Servlets call backend API (via HTTP)
* After login → redirect to user list

---

### 3. Docker Setup (MANDATORY)

You must provide:

### Services:

* `web` → JSP/Servlet app (Tomcat)
* `api` → backend Web Service
* `db` → PostgreSQL or MySQL

### Requirements:

* Use `docker-compose.yml`
* Proper networking between services
* Environment variables for DB config
* Persistent DB volume

---

## Project Structure (Expected)

```
project-root/
│
├── docker-compose.yml
│
├── frontend/
│   ├── Dockerfile
│   └── (JSP + Servlets code)
│
├── backend/
│   ├── Dockerfile
│   └── (REST API code)
│
├── db/
│   └── init.sql
│
└── README.md
```

---

## Testing Requirements

* Backend must be testable via **Postman**
* Provide example requests in README
* JSP app must:

  * Register user
  * Login
  * Display users

---

## UML Requirement

Generate a **Class Diagram** including:

* User entity
* Controllers (Servlets + API)
* Services
* Repositories

---

## Demo Requirements

The system should support demonstrating:

* Registration flow
* Login flow
* Viewing users
* Architecture explanation
* MVC explanation

Make a FLOW.md to show the presenter how to record the DEMO. Make it clean, simple and only a few lines. 

---

## README Requirements (VERY IMPORTANT)

README must include:

### 1. Setup Instructions

#### Run with Docker (PRIMARY)

```bash
docker-compose up --build
```

#### Optional Local Run (SECONDARY)

Explain how to run:

* Tomcat
* Backend
* Database

---

### 2. Tech Stack

* Java (JSP, Servlets, JAX-RS)
* PostgreSQL/MySQL
* Docker

---

### 3. API Endpoints

List all endpoints with examples.

---

### 4. How to Test

* Postman examples
* App URLs (e.g. `http://localhost:8080`)

---

### 5. Architecture Overview

Explain:

* MVC separation
* Frontend ↔ Backend interaction
* Docker services

---

## Implementation Notes (IMPORTANT FOR AI)

* Prefer **REST over SOAP**
* Keep it simple (this is a school assignment, not production infra)
* Use clean, readable code (not over-engineered)
* Focus on:

  * functionality
  * clarity
  * structure

---

## Output Expectations

The final output must include:

* Full working code
* Docker setup
* JSP frontend
* REST backend
* Database integration
* README
* UML diagram (can be Mermaid)

---

## (Optional but Good)

* Basic validation (email format, empty fields)
* Password hashing (bonus but not required)
