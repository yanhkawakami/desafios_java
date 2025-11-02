package com.desafios.pontos_de_interesse.controllers.handlers;

import com.desafios.pontos_de_interesse.dto.ApiErrorDTO;
import com.desafios.pontos_de_interesse.dto.ValidationErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiErrorDTO handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        String field = ex.getParameterName();
        String message = String.format("O parâmetro obrigatório '%s' não foi informado.", field);

        // 1. Cria um único erro de validação
        ValidationErrorDTO error = new ValidationErrorDTO(field, message);

        // 2. Cria o DTO de erro principal com uma lista contendo esse único erro
        return new ApiErrorDTO(HttpStatus.BAD_REQUEST, List.of(error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {

        // 1. Mapeia cada violação para nosso novo DTO 'ValidationError'
        List<ValidationErrorDTO> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String path = violation.getPropertyPath().toString();
                    String field = path.substring(path.lastIndexOf('.') + 1);
                    String message = violation.getMessage();
                    return new ValidationErrorDTO(field, message);
                })
                .collect(Collectors.toList());

        // 2. Cria o DTO de erro principal com a lista de erros detalhados
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
