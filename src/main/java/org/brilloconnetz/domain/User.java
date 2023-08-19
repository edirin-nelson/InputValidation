package org.brilloconnetz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

    private static final String MIN_USERNAME_LENGTH_ERROR = "Username: Minimum length is 4 characters";
    private static final String REQUIRED_FIELD_ERROR = "Required field";
    private static final String INVALID_EMAIL_ERROR = "Email: Invalid email format";
    private static final String WEAK_PASSWORD_ERROR = "Password: Not a strong password. Must have at least 1 uppercase letter, 1 special character, 1 digit, and be at least 8 characters long";
    private static final String TOO_YOUNG_ERROR = "Date of Birth: Must be 16 years or older";


    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();

        validateUsername(result);
        validateEmail(result);
        validatePassword(result);
        validateDateOfBirth(result);

        return result;
    }

    private void validateUsername(ValidationResult result) {
        if (username == null || username.length() < 4) {
            result.addFailure(MIN_USERNAME_LENGTH_ERROR);
        }

        if (username == null || username.isEmpty()) {
            result.addFailure("Username: " + REQUIRED_FIELD_ERROR);
        }
    }

    private void validateEmail(ValidationResult result) {
        if (email == null || !isValidEmail(email)) {
            result.addFailure(INVALID_EMAIL_ERROR);
        }

        if (email == null || email.isEmpty()) {
            result.addFailure("Email: " + REQUIRED_FIELD_ERROR);
        }
    }

    private void validatePassword(ValidationResult result) {
        if (password == null || !isValidPassword(password)) {
            result.addFailure(WEAK_PASSWORD_ERROR);
        }

        if (password == null || password.isEmpty()) {
            result.addFailure("Password: " + REQUIRED_FIELD_ERROR);
        }
    }

    private void validateDateOfBirth(ValidationResult result) {
        if (dateOfBirth == null) {
            result.addFailure("Date of Birth: " + REQUIRED_FIELD_ERROR);
        } else {
            try {
                LocalDate today = LocalDate.now();
                Period age = Period.between(dateOfBirth, today);
                if (age.getYears() < 16) {
                    result.addFailure(TOO_YOUNG_ERROR);
                }
            } catch (DateTimeParseException e) {
                result.addFailure("Date of Birth: Invalid date format. Please use yyyy-MM-dd format.");
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