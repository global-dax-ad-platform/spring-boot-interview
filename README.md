# Pair Programming Interview

## Overview

This is a simplified Spring Boot application representing an advertising planning system. The core entities are:

- **Order**: A booking in the platform that groups several advertising campaigns together. It has a status, delivery type, and belongs to a business.
- **Campaign**: An individual advertising campaign associated with an order. It inherits a delivery type and belongs to the same business as its order.

Data is stored in-memory (no database required) and the application exposes REST endpoints to manage orders and campaigns. The focus of this exercise is on implementing business logic and validation rules, as well as writing unit tests to ensure correctness.

## Repo Structure

```
src/
├── main/java/com/global/dax/interview/
│   ├── InterviewApplication.java       # Spring Boot entry point
│   ├── model/
│   ├── repository/                     # In-memory repositories
│   ├── service/
│   ├── validation/
│   ├── exception/
│   └── web/
└── test/java/com/global/dax/interview/web/
```

## Build

```bash
./gradlew build
```

## Run Tests

```bash
./gradlew test
```

## Start the App

```bash
./gradlew bootRun
```

The application starts on `http://localhost:8080`.