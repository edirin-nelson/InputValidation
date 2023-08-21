package org.brilloconnetz.input_validation_app.enums;

public enum ErrorMessages {
    MIN_USERNAME_LENGTH("Username: Minimum length is 4 characters"),
    REQUIRED_FIELD("Required field"),
    INVALID_EMAIL("Email: Invalid email format"),
    WEAK_PASSWORD("Password: Not a strong password. Must have at least 1 uppercase letter, 1 special character, 1 digit, and be at least 8 characters long"),
    TOO_YOUNG("Date of Birth: Must be 16 years or older"),
    INVALID_DATE_FORMAT("Date of Birth: Invalid date format. Please use yyyy-MM-dd format.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}