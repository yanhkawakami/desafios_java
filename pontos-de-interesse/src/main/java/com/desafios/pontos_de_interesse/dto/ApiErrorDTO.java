package com.desafios.pontos_de_interesse.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiErrorDTO {
    private HttpStatus status;
    private List<ValidationErrorDTO> errors; // Alterado de List<String>

    public ApiErrorDTO(HttpStatus status, List<ValidationErrorDTO> errors) {
        this.status = status;
        this.errors = errors;
    }

    // Getters
    public HttpStatus getStatus() {
        return status;
    }

    public List<ValidationErrorDTO> getErrors() {
        return errors;
    }
}