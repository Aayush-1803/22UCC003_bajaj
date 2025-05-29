# Spring Boot Webhook Solution

This Spring Boot application automatically:
1. Generates a webhook by sending a POST request on startup
2. Submits a SQL solution to the provided webhook URL using JWT authentication

## Prerequisites
- Java 11 or higher
- Maven

## Building the Application
```bash
mvn clean package
```

## Running the Application
```bash
java -jar target/_UCC003-0.0.1-SNAPSHOT.jar
```

The application will:
1. Start up
2. Generate a webhook
3. Submit the SQL solution
4. Exit automatically

## Implementation Details
- Uses Spring Boot's `RestTemplate` for HTTP requests
- Implements webhook generation and solution submission in a service
- Runs automatically on startup using `CommandLineRunner`
- Uses JWT token from webhook response for authentication 