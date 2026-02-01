# Assignment 4 – SOLID Architecture & Advanced OOP Features

## Project Overview

This project is a **Car Rental System** implemented in Java using a **SOLID layered architecture**. It demonstrates advanced Object-Oriented Programming concepts, JDBC database interaction, and clean separation of responsibilities across layers.

The system allows:

* Managing cars (CRUD)
* Renting cars with availability checks
* Applying business rules in the service layer
* Persisting data using PostgreSQL (JDBC)

---

## Architecture Overview

The project follows a strict layered architecture:

```
controller → service → repository → database
```

Each layer has a single responsibility and communicates only with adjacent layers.

---

## SOLID Principles

### 1. Single Responsibility Principle (SRP)

* **Controller**: Starts application flow and delegates work
* **Service**: Contains business rules and validation
* **Repository**: Handles only database access (JDBC)
* **Model**: Represents domain entities

Each class has one clearly defined purpose.

---

### 2. Open–Closed Principle (OCP)

* `BaseEntity` is an abstract class
* New entity types (e.g., `ElectricCar`) can be added without modifying existing logic

Example:

```java
Car car = new ElectricCar(...);
```

---

### 3. Liskov Substitution Principle (LSP)

* `ElectricCar` extends `Car`
* Any `ElectricCar` can be used where a `Car` is expected

Example:

```java
Car car = new ElectricCar(...);
System.out.println(car.getDescription());
```

---

### 4. Interface Segregation Principle (ISP)

Small, focused interfaces are used:

* `PricedItem` – price calculation behavior
* `Validatable` – validation behavior

No class is forced to implement unnecessary methods.

---

### 5. Dependency Inversion Principle (DIP)

* Services depend on **repository interfaces**, not concrete implementations
* Repositories implement generic interfaces

Example:

```java
CrudRepository<Car>
```

This allows easy replacement or extension of data access logic.

---

## Core OOP Design

### Abstract Base Class

`BaseEntity`

* Fields: `id`, `name`
* Abstract methods: `getEntityType()`, `getDescription()`
* Concrete method: `basicInfo()`
* Full encapsulation with getters/setters

---

### Inheritance Hierarchy

```
BaseEntity
   ↑
  Car
   ↑
ElectricCar
```

---

### Polymorphism

```java
Car car = new ElectricCar(...);
car.getDescription();
```

The correct overridden method is called at runtime.

---

### Composition

* `Car` has an `Engine`
* Demonstrates "has-a" relationship

```java
Car → Engine
```

---

## Interfaces & Advanced Features

### Interfaces

* `PricedItem`
* `Validatable`

  * Default method: `validateNotNull()`
  * Static method: `checkPositive()`

---

### Generics

* Generic CRUD interface:

```java
CrudRepository<T>
```

Used to enforce type safety and reusability.

---

### Lambdas

Used for sorting cars by price:

```java
cars.stream()
    .sorted(Comparator.comparingDouble(c -> c.calculatePrice(1)))
```

---

### Reflection (RTTI)

Utility class demonstrates runtime inspection:

* Class name
* Fields
* Methods

```java
ReflectionUtils.printClassInfo(Car.class);
```

---

## Exception Handling

Custom exception hierarchy:

* `InvalidInputException`
* `DuplicateResourceException`
* `ResourceNotFoundException`
* `DatabaseOperationException`

Exceptions are thrown in the **service layer**, not the controller.

---

## Database Design

### Tables

* `cars`
* `rentals`

### Relationships

* `rentals.car_id` → foreign key referencing `cars.id`

### Features

* Referential integrity
* PreparedStatements (SQL injection prevention)

---

## JDBC & PreparedStatement

PreparedStatement is used to:

* Separate SQL from data
* Prevent SQL injection
* Improve performance by compiling SQL once

---

## Application Demonstration

The `Main` controller demonstrates:

* CREATE cars
* READ all cars
* SORT using lambda
* REFLECTION output
* UPDATE car status
* DELETE car

---

## How to Run

### Requirements

* Java 17+
* PostgreSQL
* JDBC Driver

### Steps

1. Create database and tables using `schema.sql`
2. Configure database connection in `DatabaseConnection`
3. Run `Main.java`

---

## Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── exception/
 ├── utils/
 └── Main.java
```

---

## What I Learned

* How SOLID principles improve maintainability
* Clean separation of responsibilities
* Importance of interfaces and dependency inversion
* Safe database access with JDBC
* Practical use of advanced Java features

---

## Conclusion

This project demonstrates a clean, scalable Java architecture applying SOLID principles, advanced OOP, JDBC, and best practices suitable for real-world backend systems.

