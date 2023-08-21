package org.brilloconnetz.input_validation_app.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResult {
    private boolean isValid; // Indicates whether the validation is successful or not
    private List<String> failures; // Stores the reasons for validation failures

    public ValidationResult() {
        isValid = true;
        failures = new ArrayList<>();
    }

    // Add a new failure reason to the list of failures
    public void addFailure(String failureReason) {
        isValid = false; // Mark the validation as unsuccessful
        failures.add(failureReason);
    }
}