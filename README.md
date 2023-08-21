# Input-Validation App

The Input-Validation App is a Java console application that demonstrates the process of input validation, generating signed JWT (JSON Web Tokens), and verifying the authenticity of JWT tokens. This application is designed to showcase best practices for handling user input, performing validation checks, generating secure tokens, and ensuring data integrity.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- [Usage](#usage)
    - [Input Validation](#input-validation)
    - [Token Generation](#token-generation)
    - [Token Verification](#token-verification)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Input-Validation App demonstrates a complete workflow for handling user input, validating input data, generating signed JWT tokens, and verifying the authenticity of tokens. The application utilizes the `io.jsonwebtoken` library for JWT operations and provides a structured and modular approach for easy maintenance and extension.

## Features

1. User Input Validation: The application guides users through inputting their details, including username, email, password, and date of birth. It performs various validation checks to ensure the provided data is valid and meets specific criteria.

2. JWT Token Generation: After successful input validation, the application generates a signed JWT token containing user information. This token is securely signed and can be used for authentication and authorization purposes.

3. Token Verification: The application provides a method to verify the authenticity of a given JWT token. It ensures that the token was signed with a valid secret key and contains valid claims.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your machine.
- Maven for building and managing dependencies.

### Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/edirin-nelson/InputValidation.git
   ```

2. Navigate to the project directory:

   ```bash
   cd consoleApp
   ```

### Configuration

1. Create a `.env` file in the root directory of the project. This file will store your JWT secret key. Add the following line to the `.env` file, replacing `your_secret_key_here` with your actual secret key:

   ```
   JWT_SECRET_KEY=your_secret_key_here
   ```

## Usage

### Input Validation

Run the application to input user details and perform validation checks:

```bash
mvn exec:java -Dexec.mainClass="org.brilloconnetz.input_validation_app.Main"
```

Follow the prompts to provide username, email, password, and date of birth. The application will guide you through the validation process and provide appropriate messages for each validation step.

### Token Generation

After successful input validation, the application will generate a signed JWT token containing user information. The token will be printed on the console.

### Token Verification

The application also provides a method to verify the authenticity of a given JWT token. This method can be invoked using the `JwtManager.verifyToken(token)` function, where `token` is the JWT token string. The result of the verification will be displayed on the console.

## Testing

To run the JUnit tests for the application, use the following command:

```bash
mvn test
```

This command will execute the JUnit tests defined in the test classes and display the test results.

## Contributing

Contributions to this project are welcome! If you find any issues or have suggestions for improvements, feel free to submit a pull request or create an issue in the repository.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as per the terms of the license.