package org.brilloconnetz.inputValidationApp;

import org.brilloconnetz.inputValidationApp.service.InputService;
import org.brilloconnetz.inputValidationApp.service.ValidationService;
import org.brilloconnetz.inputValidationApp.util.JwtUtil;
import org.brilloconnetz.inputValidationApp.domain.User;
import org.brilloconnetz.inputValidationApp.domain.ValidationResult;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        InputService input = new InputService(); // Create an instance of InputService to handle user input

        User user = input.getUserDataFromInput(); // Get user data from user input using InputService
        ValidationService validationService = new ValidationService(user); // Create a ValidationService instance for the user

        CompletableFuture<ValidationResult> validationFuture = input.performUserValidationAsync(validationService); // Validate user data asynchronously and get a CompletableFuture for the validation result

        input.processValidationResult(validationFuture, user); // Process the validation result and display appropriate messages

        JwtUtil.processToken(user); // Process the generated JWT token and verify its authenticity
    }
}