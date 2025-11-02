package com.desafios.pontos_de_interesse.dto;

public class ValidationErrorDTO {
    private String field;
    private String message;

    public ValidationErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    // Getters
    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

