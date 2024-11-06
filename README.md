# ChauffeurReservation System

## Overview
The ChauffeurReservation System allows customers to book reservations and drivers to add vehicles to their fleet. It manages relationships where:
- A **customer** can have multiple reservations (one-to-many).
- A **driver** can manage multiple vehicles (one-to-many).

The system is built using the Spring Boot framework, providing a robust back-end and secure authentication with Spring Security.

## Technologies
- **Java 17**
- **Spring Boot**
  - Spring MVC
  - Spring Data JPA
  - Spring Security
  - Thymeleaf (for templating)
- **MySQL** (as the database)
- **JDBC API**
- **Maven** (build tool)
- **IntelliJ IDEA** (IDE)

## Models

### Customer
Represents a customer who can book multiple reservations.

| Field            | Type           | Description                              |
|------------------|----------------|------------------------------------------|
| `id`             | `long`         | Unique identifier for the customer.      |
| `firstName`      | `String`       | The customer's first name.               |
| `lastName`       | `String`       | The customer's last name.                |
| `email`          | `String`       | The customer's email (used for login).   |
| `password`       | `String`       | The customer's password (encrypted).     |
| `phoneNumber`    | `String`       | The customer's phone number.             |
| `role`           | `Enum`         | The role of the customer (e.g., USER).   |
| `reservations`   | `List<Reservation>` | List of reservations for the customer.   |

### Reservation
Represents a booking made by a customer for a chauffeur service.

| Field            | Type           | Description                                  |
|------------------|----------------|----------------------------------------------|
| `id`             | `long`         | Unique identifier for the reservation.       |
| `pickupAddress`  | `String`       | The pickup address for the reservation.      |
| `dropoffAddress` | `String`       | The drop-off address for the reservation.    |
| `date`           | `LocalDate`    | The date of the reservation.                 |
| `time`           | `LocalTime`    | The time of the reservation.                 |
| `customer`       | `Customer`     | The customer associated with the reservation.|

### Driver
Represents a driver who can manage a fleet of vehicles.

| Field            | Type           | Description                              |
|------------------|----------------|------------------------------------------|
| `id`             | `long`         | Unique identifier for the driver.        |
| `firstName`      | `String`       | The driver's first name.                 |
| `lastName`       | `String`       | The driver's last name.                  |
| `email`          | `String`       | The driver's email (used for login).     |
| `password`       | `String`       | The driver's password (encrypted).       |
| `phoneNumber`    | `String`       | The driver's phone number.               |
| `role`           | `Enum`         | The role of the driver (e.g., DRIVER).   |
| `vehicles`       | `List<Vehicle>`| List of vehicles managed by the driver.  |

### Vehicle
Represents a vehicle in a driver's fleet.

| Field              | Type         | Description                                  |
|--------------------|--------------|----------------------------------------------|
| `id`               | `long`       | Unique identifier for the vehicle.           |
| `year`             | `int`        | The manufacturing year of the vehicle.       |
| `brand`            | `String`     | The brand of the vehicle (e.g., Toyota).     |
| `model`            | `String`     | The model of the vehicle.                   |
| `trim`             | `String`     | The trim level of the vehicle.              |
| `passengerCapacity`| `byte`       | The number of passengers the vehicle can carry.|
| `driver`           | `Driver`     | The driver associated with the vehicle.     |

## Features
- **Customer Management**: Customers can sign up, log in, and manage their reservations.
- **Driver Management**: Drivers can add vehicles to their fleet and view their assigned vehicles.
- **Reservation System**: Customers can make and manage multiple reservations.
- **Vehicle Fleet Management**: Drivers can manage multiple vehicles in their fleet.
- **Security**: User authentication and authorization using Spring Security.

## Setup & Installation
### Prerequisites
Ensure that you have the following installed:
- Java 17
- MySQL Server
- Maven
- IntelliJ IDEA or your preferred IDE

### Steps to Run the Project
1. **Clone the repository**:
   ```bash
   git clone https://github.com/stjean470/chauffeur_reservation_system.git
   cd chauffeur_reservation_system
   ```

2. **Set up the database**:
   - Create a database in MySQL.
   - Update the `application.properties` file in the `src/main/resources/` directory with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/limosine_service_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the project**:
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   You can run the application directly from your IDE or use the following Maven command:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**:
   Open your browser and navigate to:
   ```
   http://localhost:8080/login
   ```

## Usage
- **Customers**: Sign up, log in, and book reservations through the system.
- **Drivers**: Log in to manage your fleet of vehicles and view customer reservations.

## Project Structure
- `src/main/java/`: Contains the Java source code for the application.
- `src/main/resources/templates/`: Thymeleaf templates for the front-end.
- `src/main/resources/application.properties`: Configuration properties (e.g., database connection).

## Contributing
Feel free to fork the project and submit pull requests. All contributions are welcome!

## License
This project is licensed under the MIT License.
