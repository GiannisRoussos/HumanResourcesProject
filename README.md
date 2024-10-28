# Human Resources App

## Overview

The Human Resources App is designed to manage employee records, departments, positions, and projects within an organization. This application facilitates efficient handling of employee data, tracking their positions, and managing departmental structures.

## Features

- **Employee Management**: Add, update, and view employee details, including personal information and employment history.
- **Department Management**: Manage departments, their associated managers, and employee assignments.
- **Position Tracking**: Track various job positions, their responsibilities, and employee assignments.
- **Project Management**: Manage projects, including their start and end dates, and associated departments.
- **Time Logging**: Log hours worked by employees on various projects.
- **Salary Management**: Adjust employee salaries as needed.
- **Secure Registration**: Utilize BCrypt for secure password storage for new employees.
- **API Interaction**: Interact with the application using Postman for testing and data management.

## Database Structure

### Employee Table
- **EmployeeID** (Primary Key): Unique identifier for each employee.
- **FirstName**: Employee's first name.
- **LastName**: Employee's last name.
- **Email**: Employee's email address.
- **PhoneNumber**: Employee's phone number.
- **HireDate**: The date the employee started with the organization.
- **DepartmentID** (Foreign Key): References the Department table.

### Department Table
- **DepartmentID** (Primary Key): Unique identifier for each department.
- **DepartmentName**: Name of the department.
- **ManagerID** (Foreign Key): References Employee table; identifies the head of the department.

### Position Table
- **PositionID** (Primary Key): Unique identifier for each position.
- **Title**: Job title of the position.
- **Description**: A brief description of the position’s responsibilities.
- **DepartmentID** (Foreign Key): References the Department table.

### EmployeePosition Table
(Handles many-to-many relationship between Employees and Positions)
- **EmployeeID** (Foreign Key): References the Employee table.
- **PositionID** (Foreign Key): References the Position table.
- **StartDate**: The start date of the employee in that position.
- **EndDate**: The end date of the employee in that position (null if current).

### Project Table
- **ProjectID** (Primary Key): Unique identifier for each project.
- **ProjectName**: Name of the project.
- **StartDate**: The date the project started.
- **EndDate**: The date the project ended (or null if ongoing).
- **DepartmentID** (Foreign Key): References the Department table.

## Getting Started

### Prerequisites
- [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or higher)
- [Spring Boot](https://spring.io/projects/spring-boot) (version compatible with the project)
- [Postman](https://www.postman.com/downloads/) for API testing

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/human-resources-app.git
2. Navigate to the project directory:
   ```bash
   cd human-resources-app
3. Build the project:
   ```bash
   ./mvnw clean install
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
Usage
You can use Postman to interact with the API endpoints for managing employees, departments, positions, and projects. Make sure to authenticate and use the provided routes as necessary.
