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



# Pancake Shop Simulation

This Java application simulates a pancake shop scenario where a shopkeeper makes pancakes and three users consume them within a 30-second time slot. The goal is to determine if the shopkeeper can meet the users' demands and analyze the efficiency of both non-concurrent and concurrent implementations.

## Overview

The application consists of several classes that collaborate to simulate the pancake shop scenario:

- `User`: Represents a user with a name and maximum number of pancakes they can eat.
- `Slot`: Represents a 30-second time slot with starting and ending times.
- `PancakeShop`: Represents the pancake shop, capable of making pancakes and generating random user demands.
- `PancakeShopService`: Provides methods for processing a time slot's scenario, both non-concurrently and concurrently.
- `ConcurrentPancakeShopRunner`: Executes the concurrent scenario with user demands and shopkeeper activities.

## How to Run

1. Compile the Java files using your preferred Java compiler.
2. Run the `ConcurrentPancakeShopApp` class for the concurrent scenario.
3. Run the `NonConcurrentPancakeShopApp` class for the non-concurrent scenario.

## Code Structure

### User

Represents a user who can eat pancakes.

- Attributes:
    - `name`: The user's name.
    - `maxPancakesToEat`: The maximum number of pancakes the user can eat.

### Slot

Represents a 30-second time slot.

- Attributes:
    - `startTime`: The starting time of the time slot.
    - `endTime`: The ending time of the time slot.

### PancakeShop

Represents the pancake shop.

- Methods:
    - `makePancakes()`: Generates a random number of pancakes made by the shopkeeper.
    - `generateRandomUserDemand()`: Generates a random number of pancakes demanded by a user.
    - `canMeetUserDemands(List<Integer> userDemands)`: Checks if the shopkeeper can meet the demands of all users.

### PancakeShopService

Provides methods for processing the pancake shop scenarios.

- Methods:
    - `processSlotNonConcurrently(PancakeShop pancakeShop, Slot slot, List<User> users)`: Processes the non-concurrent scenario.
    - `processSlotConcurrently(int pancakesMade, List<Integer> userDemands)`: Processes the concurrent scenario.

### ConcurrentPancakeShopRunner

Runs the concurrent pancake shop scenario.

- Methods:
    - `runConcurrentScenario()`: Executes the concurrent scenario.

### NonConcurrentPancakeShopRunner

Runs the non-concurrent pancake shop scenario.

- Methods:
    - `runNonConcurrentScenario()`: Executes the non-concurrent scenario.

## Observations

Both non-concurrent and concurrent implementations aim to simulate the pancake shop scenario, but they use different approaches to achieve this:

### Non-Concurrent Implementation

The non-concurrent implementation executes sequentially, following a step-by-step approach. The shopkeeper makes pancakes, and then the user demands are processed one by one. This approach is straightforward but might lead to slower execution times when dealing with multiple users and shopkeeper activities.

### Concurrent Implementation

The concurrent implementation leverages the `CompletableFuture` framework to parallelize tasks. This approach allows the shopkeeper to make pancakes and users to generate demands concurrently. Once all demands are ready, the results are combined to process the time slot's scenario. This approach can lead to better performance when dealing with multiple users and shopkeeper activities.

Comparing both implementations, the concurrent version tends to provide better performance and can handle a larger number of users more efficiently. However, it's important to note that the concurrent approach introduces more complexity, and understanding the asynchronous behavior is crucial to avoid potential issues.

In conclusion, the choice between non-concurrent and concurrent implementations depends on the scale of the application, desired performance, and the developer's familiarity with asynchronous programming concepts.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as per the terms of the license.
