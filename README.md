# UserRestAPI

A RESTful API for user management built with Node.js and Express. This project provides a simple, extensible foundation for handling common user operations such as registration, authentication, profile management, and CRUD operations.

## Features

- User Registration (sign-up)
- User Authentication (login/logout)
- Retrieve all users
- Get individual user profiles
- Update and delete user records
- JSON-based RESTful endpoints
- Password hashing for security

## Tech Stack

- **Runtime:** Node.js
- **Framework:** Express.js
- **Database:** (Customizable, e.g., MongoDB, PostgreSQL, SQLite, or In-Memory)
- **Authentication:** JWT (JSON Web Tokens) or similar libraries
- **Password Security:** bcrypt or argon2

## Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/NahidAkhter/UserRestAPI.git
    cd UserRestAPI
    ```
2. **Install dependencies:**
    ```bash
    npm install
    ```
3. **Configure environment variables:**
    - Create a `.env` file and add the necessary configuration (database URL, JWT secret, etc.).

4. **Start the server:**
    ```bash
    npm run start
    ```

   The API will be accessible at `http://localhost:3000` by default.

## Example API Endpoints

| Method | Endpoint              | Description            | Auth Required |
|--------|-----------------------|------------------------|--------------|
| POST   | `/api/register`       | Register new user      | No           |
| POST   | `/api/login`          | User login             | No           |
| GET    | `/api/users`          | List all users         | Yes          |
| GET    | `/api/users/:id`      | Get user by ID         | Yes          |
| PUT    | `/api/users/:id`      | Update user            | Yes          |
| DELETE | `/api/users/:id`      | Delete user            | Yes          |

## Usage

Use tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) to interact with the API.

### Register a User

```http
POST /api/register
Content-Type: application/json

{
  "username": "jane_doe",
  "email": "jane@example.com",
  "password": "securepassword"
}
```

### Login

```http
POST /api/login
Content-Type: application/json

{
  "email": "jane@example.com",
  "password": "securepassword"
}
```

Returns a JWT token on success.

### Authenticated Requests

For routes that require authentication, add:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

## Contributing

Contributions are welcome! Please open issues or pull requests to suggest features, report bugs, or improve documentation.

## License

This project is licensed under the [MIT License](LICENSE).

---

**Author:** [NahidAkhter](https://github.com/NahidAkhter)
