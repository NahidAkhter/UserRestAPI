# UserRestAPI

A RESTful API project for user management, implemented in Java. This repository provides the backend foundation for handling typical user-related operations such as registration, authentication, profile management, and CRUD actions, suitable for integration with mobile or web applications.

## Features

- User registration and login/logout
- User profile retrieval and update
- Delete user
- Secure password storage
- JSON-based REST API endpoints

## Tech Stack

- **Language:** Java
- **Framework:** (Please refer to project source, e.g., Spring Boot or Jakarta EE, as applicable)
- **Project Structure:** The service implementation is located in the `mobile-app-ws` directory.

## Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/NahidAkhter/UserRestAPI.git
    cd UserRestAPI
    ```
2. **Navigate to the project directory:**
    ```bash
    cd mobile-app-ws
    ```
3. **Build the project** (using Maven or Gradle as configured):
    - For Maven:
      ```bash
      mvn clean install
      ```
    - For Gradle:
      ```bash
      gradle build
      ```
4. **Run the application:**
    - For Maven:
      ```bash
      mvn spring-boot:run
      ```
    - For Gradle:
      ```bash
      gradle bootRun
      ```
    - Or use your preferred Java IDE.

## Example Endpoints

| Method | Endpoint            | Description          |
| ------ | ------------------- | -------------------- |
| POST   | `/users/register`   | Register new user    |
| POST   | `/users/login`      | Authenticate user    |
| GET    | `/users`            | List all users       |
| GET    | `/users/{id}`       | Get specific user    |
| PUT    | `/users/{id}`       | Update user details  |
| DELETE | `/users/{id}`       | Delete a user        |

> **Note:** Actual endpoint paths may vary. Please refer to code or in-app documentation.

## Contributing

Contributions are welcome! Please open an issue or pull request to suggest changes or report problems.

## License

This project is licensed under the [MIT License](LICENSE).

---

**Author:** [NahidAkhter](https://github.com/NahidAkhter)
