# Spring Boot Security Project

This repository contains a Spring Boot application that includes security features with JWT, simple CRUD operations, and an integration with a Python script to solve an optimization model. The application is automatically deployed to an Apache Tomcat server using GitHub Actions.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Deployment](#deployment)
- [API Endpoints](#api-endpoints)

## Features

- **Security with JWT**: Protects API endpoints with JWT-based authentication.
- **CRUD Operations**: Simple create, read, update, and delete operations for managing data.
- **Optimization Model**: Integrates a Python script to solve an optimization model.
- **CI/CD Pipeline**: Automated deployment to an Apache Tomcat server via GitHub Actions.

## Technologies Used

- **Java**: The main programming language.
- **Spring Boot**: A framework for building production-ready applications.
- **Spring Security**: For handling JWT-based security.
- **Python**: To solve the optimization model.
- **Apache Tomcat**: The server where the application is deployed.
- **GitHub Actions**: For CI/CD pipeline.

## Getting Started

### Prerequisites

- **Java 17 or higher**
- **Python 3.x**
- **Apache Tomcat 9 or higher**
- **Git**

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/emreakburakci/security.git
   cd security
   ./mvnw clean install


### Deployment

- The project is configured to automatically deploy to an Apache Tomcat server whenever a push is made to the repository.
- Ensure that your credentials are correct and Tomcat server is running and accessible.
- Push your changes to the repository, and the GitHub Actions pipeline will handle the rest.

### API Endpoints

#### AuthController

- **POST /api/register**
  - **Description**: Registers a new user.
  - **Request Body**: 
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
  - **Response**: 
    ```json
    {
      "message": "User registered successfully"
    }
    ```

- **POST /api/login**
  - **Description**: Authenticates a user and returns a JWT token.
  - **Request Body**: 
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
  - **Response**: 
    ```json
    {
      "token": "string"
    }
    ```

- **GET /api/hello**
  - **Description**: Returns a greeting message.
  - **Response**: 
    ```json
    {
      "message": "Hello, World!"
    }
    ```

- **GET /api/admin**
  - **Description**: Returns a message for admin users.
  - **Response**: 
    ```json
    {
      "message": "Admin Board"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN`.

- **GET /api/user**
  - **Description**: Returns a message for regular users.
  - **Response**: 
    ```json
    {
      "message": "User Board"
    }
    ```
  - **Authorization**: Requires `ROLE_USER`.

- **POST /api/logout**
  - **Description**: Logs out the user by blacklisting the JWT token.
  - **Response**: 
    ```json
    {
      "message": "Logged out successfully"
    }
    ```

#### EmployeeController

- **POST /employee/createEmployee**
  - **Description**: Creates a new employee.
  - **Request Body**: 
    ```json
    {
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN`.

- **PUT /employee/updateEmployee**
  - **Description**: Updates an existing employee.
  - **Request Body**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN`.

- **DELETE /employee/deleteEmployee/{id}**
  - **Description**: Deletes an employee by ID.
  - **Response**: 
    ```json
    {
      "message": "Employee deleted successfully"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN`.

- **GET /employee/getEmployee/{id}**
  - **Description**: Retrieves an employee by ID.
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.

- **GET /employee/getEmployeeByFirstName/{firstName}**
  - **Description**: Retrieves an employee by first name.
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.

- **GET /employee/getEmployeeByLastName/{lastName}**
  - **Description**: Retrieves an employee by last name.
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.

- **GET /employee/getEmployeeByEmailId/{emailId}**
  - **Description**: Retrieves an employee by email ID.
  - **Response**: 
    ```json
    {
      "id": "number",
      "firstName": "string",
      "lastName": "string",
      "emailId": "string"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.

- **GET /employee/getAllEmployees**
  - **Description**: Retrieves all employees with pagination.
  - **Response**: 
    ```json
    {
      "content": [
        {
          "id": "number",
          "firstName": "string",
          "lastName": "string",
          "emailId": "string"
        }
      ],
      "totalPages": "number",
      "totalElements": "number",
      "size": "number",
      "number": "number"
    }
    ```
  - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.

#### PyomoController

- **GET /pyomo/runScript**
  - **Description**: Runs a Pyomo script and returns the result.
  - **Response**: 
    ```json
    {
      "result": "string"
    }
    ```
