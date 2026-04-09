# Demo Recording Guide - User Management System

This guide walks you through recording a demo video of the User Management System application.

## Setup Before Recording

1. **Start the application**:
   ```bash
   docker-compose up --build
   ```
   Wait for all services to be ready (approximately 1-2 minutes)

2. **Open browser**:
   - Navigate to http://localhost:8080
   - Make sure page loads successfully

3. **Screen Recording Setup**:
   - Use QuickTime (macOS) or OBS Studio (any OS)
   - Set resolution to 1080p or higher
   - Ensure good audio quality if narrating

---

## Demo Flow (Approximately 10-15 minutes)

### Part 1: Introduction (1-2 minutes)

**Narration**:
> "This is a User Management System built with Java JSP/Servlets frontend and Spring Boot REST API backend. The application demonstrates the MVC architectural pattern with proper separation of concerns."

**Show**:
- Home page at http://localhost:8080
- Take screenshot showing the "User Management System" landing page

---

### Part 2: Registration Flow (2-3 minutes)

**Narration**:
> "Let's start by registering a new user. Click on the Register button."

**Actions**:
1. Click "Register" button
2. Fill in the form with:
   - Name: `Alice Johnson`
   - Email: `alice@example.com`
   - Password: `password123`
3. Click "Register" button
4. Show success message
5. System redirects to login page

**Show**:
- Registration form with all fields
- Success notification
- Automatic redirect to login

---

### Part 3: Login Flow (2-3 minutes)

**Narration**:
> "Now let's log in with the user we just created. I'll enter the email and password."

**Actions**:
1. Enter email: `alice@example.com`
2. Enter password: `password123`
3. Click "Login" button
4. Wait for redirect to users page

**Show**:
- Login form
- Processing state
- Successful redirect

---

### Part 4: User List Display (1-2 minutes)

**Narration**:
> "After logging in, we can see the list of all registered users. Let me register another user and then show you that both users appear in the list."

**Actions**:
1. Click "Logout"
2. Click "Register"
3. Register another user:
   - Name: `Bob Smith`
   - Email: `bob@example.com`
   - Password: `pass456`
4. Login with Bob's credentials
5. Show user list with both Alice and Bob

**Show**:
- User list table with multiple users
- All columns: ID, Name, Email

---

### Part 5: Architecture Explanation (3-4 minutes)

**Narration**:
> "Let me explain the architecture of this application. It follows the MVC (Model-View-Controller) pattern.

> **Frontend Layer**: We have JSP pages (register.jsp, login.jsp, users.jsp) that serve as the View. The Servlets (RegisterServlet, LoginServlet, UsersServlet) act as Controllers, handling user requests and coordinating with the backend.

> **Backend Layer**: The REST API runs on Spring Boot and exposes three main endpoints:
> - POST /register for user registration
> - POST /login for authentication
> - GET /users to retrieve all users

> **Model Layer**: The User entity represents our data model with id, name, email, and password fields.

> **Data Layer**: PostgreSQL database stores all user information persistently.

> The frontend communicates with the backend via HTTP REST calls using JSON format. This separation allows the frontend and backend to be developed and scaled independently."

**Visual Aid**:
Open the UML_DIAGRAM.md to show the class relationships

---

### Part 6: API Testing with Browser (2-3 minutes)

**Narration**:
> "Let me show you the REST API by testing it directly. I'll open a new tab and call the API endpoints directly."

**Actions**:
1. Open new browser tab
2. Navigate to: `http://localhost:8090/api/users`
3. Show JSON response with users list
4. (Optional) Use Postman to show:
   - POST to /api/register
   - POST to /api/login
   - GET from /api/users

**Show**:
- API response in JSON format
- Clean, structured data

---

### Part 7: Technical Details (2-3 minutes)

**Narration**:
> "The application uses:
> - **Java 17** for both frontend and backend
> - **Spring Boot 3** for the REST API
> - **PostgreSQL** for the database
> - **Tomcat** for serving the JSP application
> - **Docker** for containerization

> All services communicate through Docker networks. The database has a health check to ensure it's ready before the API starts. The entire application can be deployed with a single command: `docker-compose up --build`"

**Show**:
- docker-compose.yml file structure
- Database schema (db/init.sql)

---

### Part 8: MVC Pattern Deep Dive (2-3 minutes)

**Narration**:
> "Let me explain the MVC pattern implementation:

> **Model**: The User entity contains the data structure with validation. DTOs are used for data transfer between layers.

> **View**: JSP pages use JSTL (JavaServer Pages Standard Tag Library) for dynamic content rendering. The views are completely separated from business logic.

> **Controller**: 
> - Frontend: Servlets handle HTTP requests, validate input, and call the backend API
> - Backend: REST controller endpoints handle business logic

> **Service Layer**: UserService contains the business logic for registration, authentication, and user retrieval.

> **Repository Layer**: UserRepository provides database access using Spring Data JPA.

> This layered approach provides:
> - Clear separation of concerns
> - Easy testing of individual components
> - Simple maintenance and future enhancements
> - Better code organization and readability"

**Show**:
- Source code structure
- Service class implementation
- Controller class

---

### Part 9: Error Handling Demo (1-2 minutes)

**Narration**:
> "Let me show you how the application handles errors. I'll try to register with a duplicate email."

**Actions**:
1. Click Logout (if needed)
2. Click Register
3. Try to register with: `alice@example.com` (same as before)
4. Show error message

**Show**:
- Error message display
- Form remains on page with error highlighted

---

### Part 10: Conclusion (1 minute)

**Narration**:
> "This application demonstrates professional web development practices including:
> - Clean MVC architecture
> - REST API design principles
> - Proper database design
> - Docker containerization for easy deployment
> - User-friendly interface
> - Input validation and error handling

> The code is well-documented, maintainable, and easily extensible. Thank you for watching!"

**Show**:
- Home page again
- Brief summary of features

---

## Recording Tips

1. **Smooth Transitions**: Use slow clicks and deliberate actions so the viewer can follow
2. **Clear Audio**: Speak slowly and clearly, avoid filler words
3. **Pacing**: Pause between sections for readability
4. **Show Code**: Display relevant source files when explaining architecture
5. **Test First**: Run through the demo once before recording
6. **Lighting**: Ensure good lighting so code is readable

---

## Troubleshooting During Demo

| Issue | Solution |
|-------|----------|
| Application slow | Wait longer, services need time to start |
| Cannot register | Check if database is running: `docker ps` |
| Blank user list | Refresh page (F5) |
| API endpoint not responding | Check backend logs: `docker-compose logs api` |
| Frontend not loading | Check frontend logs: `docker-compose logs web` |

---

## Total Demo Time
- Without pauses: **12-15 minutes**
- With natural breaks: **15-18 minutes**

This length is appropriate for a comprehensive demonstration while maintaining viewer engagement.

---

**Tip**: Record the demo in sections and take breaks between parts. This allows for easier editing if you make mistakes.
