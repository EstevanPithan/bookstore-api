# Bookstore API

Bookstore API is a Spring Boot application designed to manage a bookstore's inventory, customers, and sales. It offers RESTful endpoints for managing books, customers, orders, and other bookstore-related data.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Technologies](#technologies)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage books (CRUD operations)
- Manage customers (CRUD operations)
- Manage orders (CRUD operations)
- Secure endpoints with Spring Security
- Documentation with Swagger

## Installation

### Prerequisites

- Java 17 or higher
- Maven
- MySQL

### Steps

1. **Clone the repository**
    ```sh
    git clone https://github.com/your-username/bookstore-api.git
    cd bookstore-api
    ```

2. **Configure the database**
    - Create a MySQL database named `bookstore`.
    - Update the `application.properties` file with your MySQL credentials.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project**
    ```sh
    mvn clean install
    ```

4. **Run the application**
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access the Swagger UI for API documentation and testing at:
http://localhost:8080/swagger-ui.html

## Endpoints

### Books

- `GET /api/books` - List all books
- `GET /api/books/{id}` - Get a book by ID
- `POST /api/books` - Create a new book
- `PUT /api/books/{id}` - Update a book
- `DELETE /api/books/{id}` - Delete a book

### Customers

- `GET /api/customers` - List all customers
- `GET /api/customers/{id}` - Get a customer by ID
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update a customer
- `DELETE /api/customers/{id}` - Delete a customer

### Orders

- `GET /api/orders` - List all orders
- `GET /api/orders/{id}` - Get an order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update an order
- `DELETE /api/orders/{id}` - Delete an order

## Technologies

- **Spring Boot** - Framework for building the application
- **Spring Data JPA** - For database interactions
- **Spring Security** - For securing the application
- **MySQL** - Database
- **Swagger** - API documentation
- **Lombok** - To reduce boilerplate code
- **ModelMapper** - For object mapping

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss what you would like to change.

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
