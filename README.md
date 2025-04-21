
# Spring Task Manager App

A full-featured Task Management web application built using Spring Boot. It allows users to register, log in (via database or Google), and manage their personal tasks efficiently.

## Features
- User Registration & Login (with BCrypt password encoding)
- Google OAuth2 Login Support
- Add, Update, Complete, and Delete Tasks
- Each user sees only their own tasks
- Task priorities and due dates
- Clean UI with Bootstrap (Light UI)
- Secure routes using Spring Security

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA + Hibernate
- MySQL
- Thymeleaf
- Bootstrap 5
- Google OAuth2

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/adarsh2907/spring-task-manager-app.git
cd spring-task-manager-app
```

### 2. Set Up MySQL
- Create a database named: `task_manager_db`
- Run the SQL scripts provided in `src/main/resources/sql/` to create tables.

### 3. Configure `application.properties`
- Copy `application-example.properties` to `application.properties` and update the credentials:
  ```properties
  spring.datasource.username=your_db_username
  spring.datasource.password=your_db_password
  
  spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
  spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
  ```

### 4. Run the App
- Use IntelliJ or run from the terminal:
  ```bash
  ./mvnw spring-boot:run
  ```
  The app will start at: `http://localhost:8080`

## Google Login Setup
- Go to Google Cloud Console
- Create OAuth 2.0 credentials
- Set the redirect URI to: `http://localhost:8080/login/oauth2/code/google`
- Paste the client ID & secret in your `application.properties`.

## Folder Structure
```
├── controller
├── entity
├── repository
├── service
├── security
├── templates (Thymeleaf)
└── resources
```
