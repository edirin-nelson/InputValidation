package org.brilloconnetz;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        InputManager input = new InputManager();

        User user = input.getUserDataFromInput();

        CompletableFuture<ValidationResult> validationFuture = input.validateUserAsync(user);

        input.processValidationResult(validationFuture, user);
    }
}