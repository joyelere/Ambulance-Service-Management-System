# Ambulance Service Management System

## Project Overview

The Ambulance Service Management System is designed to streamline the management of ambulance services for various users such as hospitals, individuals, primary healthcare centers, and traffic patrol teams. The system allows for efficient handling of ambulance requests, tracking the availability of medical staff and drivers, and managing the assignment of ambulances during emergencies.

## Tools and Technologies Used

- **Java 17**: The primary programming language used for development.
- **Spring Boot 3.0**: A robust framework that simplifies the development of Java applications by providing pre-configured setups and a suite of tools for building standalone, production-grade Spring applications.
- **Spring Security**: A powerful and highly customizable authentication and access-control framework for securing the application. It supports various authentication mechanisms and provides out-of-the-box integration with JWT.
- **JPA (Java Persistence API)**: Used for database operations and mapping Java objects to database tables. It simplifies database interactions by providing a high-level abstraction for CRUD operations.
- **Lombok**: A Java library that helps to reduce boilerplate code by automatically generating getters, setters, and other common methods at compile time.
- **Maven**: A build automation tool used for managing project dependencies and building the application.
- **H2 Database**: An in-memory database used for development and testing purposes. It allows for quick setup and easy testing without requiring a separate database server.
- **MySQL**: A relational database management system used for storing persistent data.

## Session Management with Cookies

The application uses session management via cookies. When a user logs in, a `JSESSIONID` cookie is generated and sent in the response headers. This session ID is used to manage the user's session across different requests, ensuring secure and persistent authentication throughout the user's interaction with the application.

## Endpoints

### Authentication and User Management
- **POST /auth/login**: Authenticates a user and generates a `JSESSIONID` cookie.
- **POST /auth/register**: Registers a new user (Admin or User by defalut).
  - **Example Input (Postman)**:
    ```json
    {
        "username": "joyelere",
        "password": "temiloluwa33",
        "role": "ADMIN",
        "userType": "INDIVIDUAL"    
    }
    ```
- **GET /users**: Retrieves a list of all users.
- **GET /users/type/{userType}**: Retrieves a list of all users userType.
- **GET /users/{username}**: Retrieves user by username.
  

### Ambulance Management
- **GET /users/ambulance**: Retrieves a list of all ambulances.
- **POST /admin/addAmbulance**: Adds a new ambulance to the system.
  - **Example Input (Postman)**:
    ```json
    {
        "licensePlate": "XYZ123",
        "location": Ogba
    }
    ```
- **PUT /users/ambulance/update/{id}**: Updates the details of an existing ambulance.
  - **Example Input (Postman)**:
    ```json
    {
        "licensePlate": "XYZ 123",
        "location": Ogba,
        "status": "IN_SERVICE"
    }
    ```

### Ambulance Request Management
- **POST /users/ambulance/request/{ambulanceId}**:Allows authenticated users to create a new ambulance request by specifying the Id of the Ambulance available and close to ther location.
  - **Example Input (Postman)**:
    ```url
        /users/ambulance/request/2       
    ```
- **GET /users/ambulance/request/{ambulanceRequestId}**: Retrieves the details of a specific ambulance request.
- **PUT /users/ambulance/request/{ambulanceRequestId}**: Allows admin users to update an existing ambulance request, assigning an attendee and a driver to the request.
  - **Example Input (Postman)**:
    ```json
    {
        "attendeeId": 1,
        "driverId": 2
    }
    ```
- **GET /users/ambulance/request**: Retrieves a list of all ambulance requests.

### Employee Management
- **GET /users/employee**: Retrieves a list of all employees.
- **POST /admin/addEmployee**: Adds a new employee to the system.
  - **Example Input (Postman)**:
    ```json
    {
        "name": "Joshua",
        "specialty": "Driver"
    }
    ```

### Attendee Management
- **GET /users/attendee**: Retrieves a list of all attendees.
- **POST /admin/addAttendee**: Adds a new attendee to the system.
  - **Example Input (Postman)**:
    ```json
    {
        "name": "Sarah",
        "specialty": "Nurse"
    }
    ```

### Doctor Management
- **GET /users/doctor**: Retrieves a list of all attendees.
- **POST /admin/addDoctor**: Adds a new doctor to the system.
  - **Example Input (Postman)**:
    ```json
    {
        "name": "Jeremiah",
        "specialty": "Cardiologist"
    }
    ```

## Detailed Explanation

### Ambulance Request Management

This feature allows users to create and manage ambulance requests. When a user makes a request, the system captures the necessary details and stores them in the database. Admin users can then assign available medical staff (attendees) and drivers to the requests. The system ensures that once an attendee or driver is assigned to a request, their status is updated to "UNAVAILABLE" to prevent double assignment.

### Employee and Attendee Status Management

The system maintains the status of employees (drivers) and attendees (medical staff) to track their availability. By default, all employees and attendees are marked as "AVAILABLE". When an admin assigns them to an ambulance request, their status changes to "UNAVAILABLE". This prevents them from being assigned to multiple requests simultaneously. Once the request is completed, their status can be updated back to "AVAILABLE".

### Ambulance Management

The system also manages ambulances, tracking their availability and status. When an ambulance is assigned to a request, its status is updated to "IN_SERVICE", and when it is available for new assignments, its status is updated to "AVAILABLE". This helps in efficiently managing the fleet of ambulances and ensuring that they are utilized effectively.

### Authentication and Authorization

The application uses Spring Security to secure endpoints and manage user authentication and authorization. It supports role-based access control, ensuring that only authorized users can access specific endpoints. For example, only admin users can update ambulance requests or manage employees and attendees.

### Session Management

The application employs session management using cookies. Upon successful login, a `JSESSIONID` cookie is generated and included in the response headers. This cookie is used for subsequent requests to maintain the user's session.

### Exception Handling

The system includes robust exception handling mechanisms to ensure a smooth user experience. If any required entity (e.g., AmbulanceRequest, Attendee, or Employee) is not found, or if an attendee or driver is unavailable, appropriate error messages are returned to the user.

### Testing with Postman

To test the endpoints, you can use Postman with the appropriate HTTP methods and payloads. Ensure you include the necessary headers, such as `Content-Type: application/json` and use the `JSESSIONID` cookie for authenticated requests.

## Video Presentation

https://drive.google.com/file/d/1Zfu4E2L3DGHEkjpE9Ha8qnkw4CJlwoqL/view?usp=sharing

## Summary

This project demonstrates the use of Java Spring Boot for building a comprehensive backend application for managing ambulance services. It showcases best practices in Java development, including the use of JPA for database interactions, Spring Security for securing the application, and proper exception handling for a seamless user experience.

