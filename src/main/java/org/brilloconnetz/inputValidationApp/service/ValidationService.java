package org.brilloconnetz.inputValidationApp.service;

import org.brilloconnetz.inputValidationApp.domain.User;
import org.brilloconnetz.inputValidationApp.domain.ValidationResult;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import static org.brilloconnetz.inputValidationApp.enums.ErrorMessages.*;

public class ValidationService {
    private final User user;

    public ValidationService(User user) {
        this.user = user;
    }

    public ValidationResult performDataValidation() {
        ValidationResult result = new ValidationResult();

        validateUsername(result);
        validateEmail(result);
        validatePassword(result);
        validateDateOfBirth(result);

        return result;
    }

    private void validateUsername(ValidationResult result) {
        if (user.getUsername() == null || user.getUsername().length() < 4) {
            result.addFailure(MIN_USERNAME_LENGTH.getMessage());
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            result.addFailure("Username: " + REQUIRED_FIELD);
        }
    }

    private void validateEmail(ValidationResult result) {
        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            result.addFailure(INVALID_EMAIL.getMessage());
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            result.addFailure("Email: " + REQUIRED_FIELD);
        }
    }

    private void validatePassword(ValidationResult result) {
        if (user.getPassword() == null || !isValidPassword(user.getPassword())) {
            result.addFailure(WEAK_PASSWORD.getMessage());
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            result.addFailure("Password: " + REQUIRED_FIELD);
        }
    }

    private void validateDateOfBirth(ValidationResult result) {
        if (user.getDateOfBirth() == null) {
            result.addFailure("Date of Birth: " + REQUIRED_FIELD);
        } else {
            try {
                LocalDate today = LocalDate.now();
                Period age = Period.between(user.getDateOfBirth(), today);
                if (age.getYears() < 16) {
                    result.addFailure(String.valueOf(TOO_YOUNG));
                }
            } catch (DateTimeParseException e) {
                result.addFailure(INVALID_DATE_FORMAT.getMessage());
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPassword(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean isLongEnough = password.length() >= 8;

        return hasUppercase && hasSpecialChar && hasDigit && isLongEnough;
    }
}
