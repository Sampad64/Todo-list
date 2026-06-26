# Todo API

A secure RESTful API for managing todos, built with Java and Spring Boot.

## Features
- Full CRUD operations for todos
- JWT based authentication and authorization
- BCrypt password hashing
- Filter todos by completion status, due date, and priority
- SLF4J logging
- PostgreSQL database

## Tech Stack
- Java 21
- Spring Boot 3.2
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- JWT (jjwt)
- Lombok

## Project Structure
```
src/main/java/com/Icy/Todo_list/
├── controller/
│   ├── TodoController.java
│   └── AuthController.java
├── service/
│   ├── TodoService.java
│   └── UserService.java
├── repository/
│   ├── TodoRepository.java
│   └── UserRepository.java
├── model/
│   ├── Todo.java
│   └── User.java
└── security/
    ├── JwtUtil.java
    ├── JwtFilter.java
    └── SecurityConfig.java
```

## Getting Started

### Prerequisites
- Java 21
- Maven
- PostgreSQL

### Setup

1. Clone the repository
```bash
git clone https://github.com/Sampad64/Todo-list.git
cd Todo-list
```

2. Create a PostgreSQL database
```sql
CREATE DATABASE todo_api;
```

3. Configure `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_api
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

4. Run the application
```bash
mvn spring-boot:run
```

API will be available at `http://localhost:8080`

## API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | /auth/register | Register new user | No |
| POST | /auth/login | Login and get JWT token | No |

### Todos
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | /todos | Get all todos | No |
| GET | /todos/{id} | Get todo by ID | No |
| POST | /todos | Create new todo | Yes |
| PUT | /todos/{id} | Update todo | Yes |
| DELETE | /todos/{id} | Delete todo | Yes |
| GET | /todos/completed | Get completed todos | No |
| GET | /todos/due?date=yyyy-MM-dd | Get todos by due date | No |
| GET | /todos/priority/{priority} | Get todos by priority | No |

## Usage Examples

### Register
```json
POST /auth/register
{
    "username": "ash",
    "password": "pikachu123"
}
```

### Login
```json
POST /auth/login
{
    "username": "ash",
    "password": "pikachu123"
}

Response:
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Create Todo (with token)
```json
POST /todos
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

{
    "title": "Buy groceries",
    "description": "Milk and eggs",
    "dueDate": "2026-06-30",
    "priority": "HIGH"
}
```

## Security
- Passwords are hashed using BCrypt
- JWT tokens expire after 24 hours
- POST, PUT, DELETE endpoints require a valid JWT token
- GET endpoints are publicly accessible

## Author
Sampad — [GitHub](https://github.com/Sampad64)
