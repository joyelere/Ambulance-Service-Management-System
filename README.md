# Ambulance Service Management System

## Overview

The Ambulance Service Management System is a comprehensive application designed to streamline ambulance services for registered users, including hospitals, individuals, primary healthcare centers, and traffic patrol teams. The system facilitates the management of ambulance requests, staff assignments, and ambulance statuses. Admins can manage doctors, employees, and attendees, as well as monitor and dispatch ambulances during emergencies.
## Features

- **Ambulance Management**: Add, update, and retrieve ambulances.
- **Ambulance Request Management**: Request ambulances, update requests, and view request details.
- **Attendee Management**: Add and retrieve attendees.
- **Doctor Management**: Add and retrieve doctors.
- **Employee Management**: Add and retrieve employees.
- **User Management**: Register, authenticate, and retrieve users.

## Tools/Technologies Used

- **Java Spring Boot**: Framework for building the backend application.
- **Hibernate/JPA**: ORM for database interactions.
- **PostgreSQL/MySQL**: Relational database (configuration needed).
- **Lombok**: Library to reduce boilerplate code.
- **Spring Security**: For authentication and authorization using cookies.
- **Maven**: Build automation tool.

## Authentication and Authorization

The system uses **Spring Security** to manage authentication and authorization. 

### Cookies

- **JSESSIONID**: Used for session management. After a successful login, a `JSESSIONID` cookie is set to track the user's session. This cookie should be included in subsequent requests to authenticate the user and maintain session state.

### User Permissions

- **Regular Users**: Can only view their own ambulance requests. They cannot view the requests made by other users.
- **Admin Users**: Have full access to view and manage all ambulance requests and can perform administrative tasks across the system.

## API Endpoints

### Ambulance Management

#### 1. Add Ambulance

- **Endpoint**: `POST /admin/addAmbulance`
- **Request Body**:
    ```json
    {
        "licensePlate": "ABC-1234",
        "status": "AVAILABLE",
        "location": "City Center"
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 2. Update Ambulance

- **Endpoint**: `PUT /users/ambulance/update/{id}`
- **Request Body**:
    ```json
    {
        "licensePlate": "XYZ-5678",
        "status": "IN_SERVICE",
        "location": "Downtown"
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 3. Get All Ambulances

- **Endpoint**: `GET /users/ambulance`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "licensePlate": "ABC-1234",
            "status": "AVAILABLE",
            "location": "City Center"
        },
        ...
    ]
    ```
- **Authentication**: Include `JSESSIONID` cookie.

### Ambulance Request Management

#### 1. Request Ambulance

- **Endpoint**: `POST /users/ambulance/request/{ambulanceId}`
-
- **Authentication**: Include `JSESSIONID` cookie.

#### 2. Update Ambulance Request

- **Endpoint**: `PUT /users/ambulance/request/{ambulanceRequestId}`
- **Request Body**:
    ```json
    {
        "attendeeId": 1,
        "driverId": 2
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 3. Get Ambulance Request By ID

- **Endpoint**: `GET /users/ambulance/request/{ambulanceRequestId}`
- **Response**:
    ```json
    {
        "id": 1,
        "ambulance": {
            "id": 1,
            "licensePlate": "ABC-1234",
            "status": "IN_SERVICE",
            "location": "City Center"
        },
        "status": "ASSIGNED",
        "attendee": {
            "id": 1,
            "name": "Dr. Smith",
            "specialty": "Cardiology",
            "status": "UNAVAILABLE"
        },
        "driver": {
            "id": 2,
            "name": "John Doe",
            "specialty": "Driver",
            "status": "UNAVAILABLE"
        },
        "user": {
            "id": 3,
            "username": "john_doe",
            "role": "USER",
            "userType": "INDIVIDUAL"
        }
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

### Attendee Management

#### 1. Add Attendee

- **Endpoint**: `POST /admin/addAttendee`
- **Request Body**:
    ```json
    {
        "name": "Dr. Smith",
        "specialty": "Cardiology",
        "status": "AVAILABLE"
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 2. Get All Attendees

- **Endpoint**: `GET /users/attendee`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "name": "Dr. Smith",
            "specialty": "Cardiology",
            "status": "AVAILABLE"
        },
        ...
    ]
    ```
- **Authentication**: Include `JSESSIONID` cookie.

### Doctor Management

#### 1. Add Doctor

- **Endpoint**: `POST /admin/addDoctor`
- **Request Body**:
    ```json
    {
        "name": "Dr. Alice",
        "specialty": "Pediatrics"
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 2. Get All Doctors

- **Endpoint**: `GET /users/doctor`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "name": "Dr. Alice",
            "specialty": "Pediatrics"
        },
        ...
    ]
    ```
- **Authentication**: Include `JSESSIONID` cookie.

### Employee Management

#### 1. Add Employee

- **Endpoint**: `POST /admin/addEmployee`
- **Request Body**:
    ```json
    {
        "name": "John Doe",
        "specialty": "Driver",
        "status": "AVAILABLE"
    }
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 2. Get All Employees

- **Endpoint**: `GET /users/employee`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "name": "John Doe",
            "specialty": "Driver",
            "status": "AVAILABLE"
        },
        ...
    ]
    ```
- **Authentication**: Include `JSESSIONID` cookie.

### User Management

#### 1. Register User

- **Endpoint**: `POST /users/register`
- **Request Body**:
    ```json
    {
        "username": "john_doe",
        "password": "password123",
        "role": "ADMIN" ("USER is by default"),
        "userType": "INDIVIDUAL"
    }
    ```

#### 2. Authenticate User

- **Endpoint**: `POST /users/login`
- **Request Body**:
    ```json
    {
        "username": "john_doe",
        "password": "password123"
    }
    ```
- **Notes**: After authentication, the `JSESSIONID` cookie will be set. This cookie should be included in subsequent requests.

#### 3. Get users by userType

- **Endpoint**: `GET /users//type/{userType}`
- **Response**:
    ```json
    [
        {
            "id": 1,
            "username": "john_doe",
            "role": "USER",
            "userType": "INDIVIDUAL"
        },
        ...
    ]
    ```
- **Authentication**: Include `JSESSIONID` cookie.

#### 4. Logout User

- **Endpoint**: `POST /users/logout`
- **Description**: Logs out the user by invalidating the session and clearing the `JSESSIONID` cookie.
- **Response**:
    ```json
    {
        "message": "Logout successful"
    }
    ```

## Testing with Postman

1. **Import Collection**: You can import the Postman collection file (`ambulance_service.postman_collection.json`) to get started with testing the APIs.

2. **Set Up Environment**: Configure the environment variables in Postman for the base URL and any required authentication tokens.

3. **Authenticate**: Use the `POST /users/authenticate` endpoint to log in and obtain the `JSESSIONID`. Ensure this cookie is included in subsequent requests to maintain authentication.

4. **Send Requests**: Use the Postman UI to send requests to the endpoints listed above and verify the responses.

## Configuration

1. **Database Configuration**: Update `application.properties` or `application.yml` with your database connection details.

2. **Security Configuration**: Adjust Spring Security settings as required for your application's security needs.

3. **Environment Variables**: Set any additional environment variables for your application, such as API keys or external service URLs.

## Running the Application

1. **Build**: Use Maven to build the application:
    ```bash
    mvn clean install
    ```

2. **Run**: Start the application using:
    ```bash
    mvn spring-boot:run
    ```

3. **Access**: Navigate to `http://localhost:8080` in your web browser or Postman to interact with the API.

## Video Presentation

For a detailed walkthrough of the project, including code explanations and functionality demonstrations, please watch the video presentation:

[Watch the Video Presentation](#) <!-- Replace # with the actual video link -->

