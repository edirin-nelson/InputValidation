package org.brilloconnetz.input_validation_app;

import org.brilloconnetz.input_validation_app.service.InputService;
import org.brilloconnetz.input_validation_app.service.ValidationService;
import org.brilloconnetz.input_validation_app.util.JwtUtil;
import org.brilloconnetz.input_validation_app.model.User;
import org.brilloconnetz.input_validation_app.model.ValidationResult;

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