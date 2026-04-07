# JWT Login System (Spring Boot + PostgreSQL)

## 📌 Overview

This project is a simple **JWT Authentication System** built using **Java Spring Boot**, **Spring Security**, and **PostgreSQL**. It allows users (employees) to:

* Register with a username and password
* Store passwords securely using BCrypt hashing
* Login and receive a JWT token
* Handle proper HTTP status responses (200, 401, 500)

---

## ⚙️ Tech Stack

* **Backend:** Java 17, Spring Boot 4
* **Security:** Spring Security, JWT (JJWT)
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA (Hibernate)
* **Build Tool:** Maven

---

## 📁 Project Structure

```
com.example.base
│
├── controller        # REST Controllers (AuthController)
├── dto               # Request/Response Objects
├── model             # Entity classes (Employee)
├── repository        # JPA Repositories
├── security          # JWT + Security Config
├── service           # Business Logic
└── JwtLoginApplication.java
```

---

## 🗄️ Database Setup

### 1. Create Database

```sql
CREATE DATABASE jwt_login;
```

### 2. Configure `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwt_login
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> The `employees` table will be created automatically.

---

## 🔐 API Endpoints

### ✅ Register Employee

**POST** `/api/auth/register`

#### Request Body

```json
{
  "username": "erron",
  "password": "123456"
}
```

#### Response

* `200 OK` → Employee registered
* `500 Internal Server Error` → Registration failed

---

### 🔑 Login

**POST** `/api/auth/login`

#### Request Body

```json
{
  "username": "erron",
  "password": "123456"
}
```

#### Response

* `200 OK`

```json
{
  "token": "your-jwt-token"
}
```

* `401 Unauthorized` → Invalid credentials

---

## 🔒 Password Security

* Passwords are **hashed using BCrypt** before storing in the database
* Raw passwords are never saved

---

## 🧪 Testing

### Run Application

```bash
mvn spring-boot:run
```

### Run Tests

```bash
mvn test
```

---

## 📌 HTTP Status Codes Used

| Code | Meaning                      |
| ---- | ---------------------------- |
| 200  | Success                      |
| 401  | Unauthorized (invalid login) |
| 500  | Server error                 |

---

## 🚀 Features

* JWT Authentication
* Password hashing (BCrypt)
* PostgreSQL integration
* Clean layered architecture
* RESTful API design

---

## 🔮 Future Improvements

* Add JWT filter for secured endpoints
* Role-based authentication (Admin/User)
* Token expiration handling (refresh tokens)
* Global exception handling

---

## 👨‍💻 Author

Developed by **Erron** 🚀
