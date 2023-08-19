package org.brilloconnetz.inputValidationApp;

import org.brilloconnetz.inputValidationApp.domain.InputManager;
import org.brilloconnetz.inputValidationApp.domain.JwtManager;
import org.brilloconnetz.inputValidationApp.domain.User;
import org.brilloconnetz.inputValidationApp.domain.ValidationResult;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        InputManager input = new InputManager(); // Create an instance of InputManager to handle user input

        User user = input.getUserDataFromInput(); // Get user data from user input using InputManager

        CompletableFuture<ValidationResult> validationFuture = input.validateUserAsync(user);// Validate user data asynchronously and get a CompletableFuture for the validation result

        input.processValidationResult(validationFuture, user); // Process the validation result and display appropriate messages

        JwtManager.processToken(user); // Process the generated JWT token and verify its authenticity
    }
}