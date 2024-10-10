<<<<<<< HEAD
# Employee-Datatable-CRUD
The Employee Management System is a web application built with Angular and Spring Boot. It features a dynamic employee table using DataTables, enabling efficient CRUD operations like listing, updating, and deleting employee records through a RESTful API for smooth data management and user interaction.
=======

# Employee Management System

## Overview

This project is an Employee Management System built using **Spring Boot** for the backend and **Angular** for the frontend. It utilizes a **MySQL** database to store employee data.

## Technologies Used

- **Backend:** Spring Boot
- **Frontend:** Angular
- **Database:** MySQL

## Features

- Employee CRUD (Create, Read, Update, Delete) operations
- User authentication and authorization
- Responsive user interface
- RESTful API for communication between frontend and backend

## Prerequisites

### Backend

- Java 17 or later
- Maven
- MySQL Server

### Frontend

- Node.js (v14 or later)
- Angular CLI

## Getting Started

### Backend Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>


2. **Configure MySQL Database:**

Create a new MySQL database for the application.
Update the application.properties file in the src/main/resources directory with your database details:

spring.datasource.url=jdbc:mysql://localhost:3306/<your-database-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update

3. **Build and Run the Backend:**

mvn clean install
mvn spring-boot:run

Frontend Setup
Navigate to the frontend directory:

cd <frontend-directory>

Install dependencies:
npm install

Run the Angular Application:
ng serve


