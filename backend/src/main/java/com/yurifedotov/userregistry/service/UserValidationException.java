package com.yurifedotov.userregistry.service;

import java.util.Map;

public class UserValidationException extends Exception {
    private final Map<String, String> validationErrors;

    public UserValidationException(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
