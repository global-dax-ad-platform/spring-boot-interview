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

## Tasks

### 1. Update get campaign to return the full parent order

`GET /campaign/{id}` currently returns only the campaign. Update it to also return the full parent order object nested within the response.

### 2. Create campaign endpoint

Add `POST /campaign` to create a new campaign. The endpoint should accept a campaign payload and apply the following validations before saving:

- The referenced order must exist
- The campaign's business must match the order's business
- The order must not be in `BOOKED` status
- The order must not be in `CANCELLED` status
- The order's delivery type must match the campaign's delivery type

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