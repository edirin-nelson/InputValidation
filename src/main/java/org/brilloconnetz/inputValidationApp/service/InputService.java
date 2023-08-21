package org.brilloconnetz.inputValidationApp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import org.brilloconnetz.inputValidationApp.util.JwtUtil;
import org.brilloconnetz.inputValidationApp.domain.User;
import org.brilloconnetz.inputValidationApp.domain.ValidationResult;

public class InputService {
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

    public CompletableFuture<ValidationResult> performUserValidationAsync(ValidationService validationService) {
        return CompletableFuture.supplyAsync(validationService::performDataValidation);
    }

    public void processValidationResult(CompletableFuture<ValidationResult> validationFuture, User user) {
        // Wait for validation to complete
        validationFuture.thenAccept(validationResult -> {
            if (validationResult.isValid()) {
                String jwt = JwtUtil.generateToken(user);
                System.out.println("JWT: " + jwt);

                // Process the token only if validation passed
                String verificationResult = JwtUtil.processToken(user);
                System.out.println("Token Verification Result: " + verificationResult);
            } else {
                System.out.println("Validation failure(s):");
                validationResult.getFailures().forEach(System.out::println);
            }
        }).join();
    }
}
