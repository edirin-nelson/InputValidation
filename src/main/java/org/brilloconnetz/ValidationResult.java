package org.brilloconnetz;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationResult {
    private boolean isValid;
    private List<String> failures;

    public ValidationResult() {
        isValid = true;
        failures = new ArrayList<>();
    }

    public void addFailure(String failureReason) {
        isValid = false;
        failures.add(failureReason);
    }

    public void merge(ValidationResult otherResult) {
        if (!otherResult.isValid()) {
            isValid = false;
            failures.addAll(otherResult.getFailures());
        }
    }
}