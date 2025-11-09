# TalkSync - Real-time Chatting Application

## Overview
TalkSync is a real-time chatting application built with Spring Boot and Java. It features user authentication, contact management, and real-time messaging capabilities.

## Features Implemented
1. User Registration and Login
2. Contact Management (add contacts, accept requests)
3. RESTful APIs for all functionality

## Prerequisites
- Java 17 or higher
- MySQL database
- Maven

## Database Setup
1. Create a MySQL database named `talksync`:
   ```sql
   CREATE DATABASE talksync;
   ```

2. Update the database credentials in `src/main/resources/application.properties`:
   ```
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

## How to Run
1. Clone the repository
2. Set up the database as described above
3. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

## Troubleshooting
If the application fails to start with a database connection error:

1. **Check if MySQL is running**:
   - On Windows, check if MySQL service is running in Services.msc
   - Look for a service named "MySQL" or "MySQL80"

2. **Verify your database credentials**:
   - Run `test-db-connection.bat` to test common credentials
   - Run `update-db-credentials.bat` to update your credentials

3. **Create the database**:
   - Connect to MySQL and run: `CREATE DATABASE IF NOT EXISTS talksync;`

4. **Check the error message**:
   - The application will now display specific guidance if it fails to start

## API Endpoints
### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login with existing credentials

### Contacts
- `POST /api/contacts/add` - Add a contact
- `GET /api/contacts/list` - Get user's contacts
- `GET /api/contacts/pending` - Get pending contact requests
- `POST /api/contacts/accept` - Accept a contact request

## Testing the Application
You can test the APIs using tools like Postman or curl.

### Example API Calls:
1. Register a user:
   ```
   POST http://localhost:8080/api/auth/register
   {
     "username": "john_doe",
     "email": "john@example.com",
     "password": "password123"
   }
   ```

2. Login:
   ```
   POST http://localhost:8080/api/auth/login
   {
     "username": "john_doe",
     "password": "password123"
   }
   ```

3. Add a contact:
   ```
   POST http://localhost:8080/api/contacts/add?userId=1&contactUsername=jane_doe
   ```

## Future Enhancements
- Real-time messaging using WebSocket
- Chat rooms and group chats
- Message history and persistence
- User profile management
- File sharing capabilities