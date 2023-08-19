package org.brilloconnetz.inputValidationApp.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class InputManager {
    public User getUserDataFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Date of Birth (yyyy-MM-dd): ");
        String dobString = scanner.nextLine();
        LocalDate dob = null;

        try {
            dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        }

        scanner.close();

        return new User(username, email, password, dob);
    }

    public CompletableFuture<ValidationResult> validateUserAsync(User user) {
        return CompletableFuture.supplyAsync(user::validate);
    }

    public void processValidationResult(CompletableFuture<ValidationResult> validationFuture, User user) {
        // Wait for validation to complete
        validationFuture.thenAccept(validationResult -> {
            if (validationResult.isValid()) {
                String jwt = JwtManager.generateToken(user);
                System.out.println("JWT: " + jwt);

                // Process the token only if validation passed
                String verificationResult = JwtManager.processToken(user);
                System.out.println("Token Verification Result: " + verificationResult);
            } else {
                System.out.println("Validation failure(s):");
                validationResult.getFailures().forEach(System.out::println);
            }
        }).join();
    }
}
