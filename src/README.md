# Car Rental Management API (Assignment 3)

## A. Project Overview

### Purpose

This project is a **Java-based Car Rental Management API** built using **Object-Oriented Programming (OOP)** principles, **JDBC**, and **PostgreSQL**. The goal is to demonstrate a clean multi-layer architecture with real database interaction, validation, business logic, and custom exception handling.

### Domain Description

The system allows:

* Managing cars (regular and electric)
* Managing customers
* Renting cars to customers

### Architecture

The project follows a **layered architecture**:

```
Controller → Service → Repository → Database (PostgreSQL)
```

---

## B. OOP Design Documentation

### Abstract Class

**BaseEntity (abstract)**

* Fields: `id`, `name`
* Abstract methods:

    * `getEntityType()`
    * `getDescription()`
* Concrete method:

    * `basicInfo()`

### Inheritance

* `Car` extends `BaseEntity`
* `ElectricCar` extends `Car`
* `Customer` extends `BaseEntity`

### Interfaces

* **Validatable** – enforces validation rules
* **PricedItem** – calculates rental price

Implemented in:

* `Car`

### Polymorphism Example

```java
BaseEntity car = new ElectricCar(...);
System.out.println(car.getDescription());
```

### Composition / Aggregation

* `Rental` contains:

    * `Car`
    * `Customer`

This models a real-world rental relationship.

---

## C. Database Description

### Database: PostgreSQL

### Tables

#### cars

* `id` (PK)
* `name`
* `price_per_day`
* `available`

#### customers

* `id` (PK)
* `name`
* `email` (UNIQUE)

#### rentals

* `id` (PK)
* `car_id` (FK → cars.id)
* `customer_id` (FK → customers.id)
* `start_date`
* `end_date`

### Sample SQL Inserts

```sql
INSERT INTO customers (name, email)
VALUES ('Damir Dusembekov', 'ddamir@mail.com');
```

---

## D. Controller / API Demonstration

The application is demonstrated using a **CLI (Main.java)**.

### Demonstrated Operations

* Create cars
* Retrieve all cars
* Rent a car
* Delete a car
* Trigger validation and business rules

### Example Output

```
BMW X5 - $90/day
Tesla Model Y (Electric, 80 kWh)
DONE
```

---

## E. How to Compile and Run

### Requirements

* Java 17+
* PostgreSQL
* IntelliJ IDEA
* Maven (or JDBC driver manually added)

### Steps

1. Create database and tables using `schema.sql`
2. Update database credentials in `DatabaseConnection.java`
3. Run `Main.java`

---

## F. Screenshots (to be added)

* Successful database connection
* CRUD operations output
* Error handling example

---

## G. Reflection

### What I Learned

* How to design an OOP-based API using abstract classes and interfaces
* How to use JDBC with PreparedStatements
* How to apply business logic in a service layer
* How to structure a real-world Java backend project

### Challenges

* JDBC configuration
* Foreign key constraints
* Layer separation

### Benefits of JDBC and Multi-layer Design

* Clear separation of concerns
* Easier maintenance and testing
* Real database interaction

---

## Project Structure

```
src/main/java
├── controller
├── service
├── repository
├── model
├── exception
└── utils
```

---

**Author:** Elnur Issayev
**Course:** Advanced OOP with JDBC
