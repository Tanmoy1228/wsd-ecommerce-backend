
# WSD E-commerce Platform API

A backend service for a new e-commerce business, built with Spring Boot (Java 17), following Test-Driven Development (TDD), and packaged for deployment with Docker. This API provides wishlist of a customer and different sales analytics.

## Features

- Customer Wishlist API - Retrieve a customer’s wishlist.
- Total Sale Amount API - Get the total sale amount for the current day.
- Max Sale Day API - Get the day with the highest sales in a given range.
- Top 5 Selling Items (All Time) - List the top 5 best-selling items based on total sale amount.
- Top 5 Selling Items (Last Month) - List the top 5 best-selling items based on number of sales in the last month.


---

## Tech Stack

- Java 17
- Spring Boot 3.2.2
- Spring Web, JPA, Validation
- PostgreSQL
- JUnit 5 / Mockito (TDD)
- Docker & Docker Compose
- Liquibase (DB migrations)
- Log4j2 / ELK Stack (Log visualization)
- Swagger UI (Springdoc OpenAPI)

---

## Setup

### Prerequisites

- **Docker + Docker Compose** – required for running the project
- **Java 17** *(optional)* – required for running or debugging the app outside Docker
- **Gradle** *(optional)* – used for local builds and testing, included via wrapper (`./gradlew`)

### Clone & Build
```
git clone https://github.com/Tanmoy1228/wsd-ecommerce-backend
cd wsd-ecommerce-backend
./gradlew clean build
```

### Run with Docker

```bash
  docker-compose up --build
```

Access the API at: `http://localhost:8088`

### Running Tests

```bash
  ./gradlew test
```

### Code Coverage

```bash
  ./gradlew jacocoTestReport
```

Then open: `build/reports/jacoco/test/html/index.html`

---

## API Endpoints

### Customer Wishlist API

`GET /api/wishlist/{customerId}`

Sample Response:
```json
[
  {
    "productId": 100,
    "productName": "iPhone 15 Pro",
    "addedDate": "2024-01-15T10:30:00"
  }
]
```

### Total Sale Amount API

`GET /api/sales/today`

Sample Response:
```json
{
  "date": "2025-01-15",
  "totalSaleAmount": "599.97"
}
```

### Max Sale Day API

`GET /api/sales/max-day`

Sample Response:
```json
{
  "date": "2025-01-15",
  "totalAmount": "599.97"
}
```

### Top 5 Selling Items (All Time)

`GET /api/sales/top-five-selling-items`

Sample Response:
```json
[
  {
    "productId": 1,
    "productName": "Laptop",
    "totalSaleAmount": 5000.00
  },
  {
    "productId": 2,
    "productName": "Phone",
    "totalSaleAmount": 3000.00
  },
  {
    "productId": 3,
    "productName": "Tablet",
    "totalSaleAmount": 2000.00
  },
  {
    "productId": 4,
    "productName": "Headphones",
    "totalSaleAmount": 1500.00
  },
  {
    "productId": 5,
    "productName": "Smartwatch",
    "totalSaleAmount": 1000.00
  }
]
```

Top 5 Selling Items (Last Month)

`GET /api/sales/last-month-top-selling-items`

Sample Response:
```json
[
  {
    "productId": 1,
    "productName": "Laptop",
    "numberOfSales": 10
  },
  {
    "productId": 2,
    "productName": "Phone",
    "numberOfSales": 15
  },
  {
    "productId": 3,
    "productName": "Tablet",
    "numberOfSales": 8
  },
  {
    "productId": 4,
    "productName": "Headphones",
    "numberOfSales": 5
  },
  {
    "productId": 5,
    "productName": "Smartwatch",
    "numberOfSales": 3
  }
]
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8088/swagger-ui.html
```

---

## Project Structure

```
src/
├── controller/
├── dto/
├── model/
├── repository/
├── service/
├── logging/
└── exception/
```

---

## Database & Initial Data
- PostgreSQL is default (changeable via application.properties).

- Liquibase runs schema migrations and seeds sample data.

---

## Log Visualization
- Log4j2 used for application logging.

- ELK Stack (Elasticsearch, Logstash, Kibana) included for log analysis and visualization.

---

## Git & Collaboration

- Issues were created to track features.

- Pull Requests (PRs) were used to submit changes, each with clear titles and descriptions.

- All changes were peer-review ready with meaningful commit messages.
