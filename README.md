# user-management

Simple Spring Boot + JPA + MySQL User Management CRUD project.

## How to run

1. Create MySQL database:
   ```sql
   CREATE DATABASE userdb;
   ```

2. Update `src/main/resources/application.properties` with your MySQL username/password.

3. Build and run:
   ```
   mvn clean package
   mvn spring-boot:run
   ```

Server runs on http://localhost:8080

APIs:
- POST /api/users
- GET /api/users
- GET /api/users/{id}
- PUT /api/users/{id}
- DELETE /api/users/{id}
