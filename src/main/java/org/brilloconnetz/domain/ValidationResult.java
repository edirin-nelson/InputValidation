package org.brilloconnetz.domain;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResult {
    private boolean isValid; // Indicates whether the validation is successful or not
    private List<String> failures; // Stores the reasons for validation failures

    public ValidationResult() {
        isValid = true; // Initialize as valid by default
        failures = new ArrayList<>(); // Initialize an empty list for failures
    }

    // Add a new failure reason to the list of failures
    public void addFailure(String failureReason) {
        isValid = false; // Mark the validation as unsuccessful
        failures.add(failureReason); // Add the failure reason to the list
    }

    // Merge the failures from another ValidationResult
    public void merge(ValidationResult otherResult) {
        if (!otherResult.isValid()) {
            isValid = false; // If the other result is not valid, mark this result as invalid
            failures.addAll(otherResult.getFailures()); // Merge the failure reasons
        }
    }
}