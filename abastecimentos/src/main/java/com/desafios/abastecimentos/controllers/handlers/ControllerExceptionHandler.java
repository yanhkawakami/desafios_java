package com.desafios.abastecimentos.controllers.handlers;

import com.desafios.abastecimentos.dto.CustomErrorDTO;
import com.desafios.abastecimentos.dto.ValidationErrorDTO;
import com.desafios.abastecimentos.services.exceptions.EmptyContent;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Manipulador global de exceções para a aplicação.
 * Intercepta exceções lançadas pelos controllers e retorna
 * respostas HTTP padronizadas com informações de erro.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Trata exceções de recurso não encontrado
     *
     * @param e exceção ResourceNotFoundException
     * @param request requisição HTTP
     * @return ResponseEntity com erro 404 (Not Found)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata exceções de integridade do banco de dados
     *
     * @param e exceção DatabaseException
     * @param request requisição HTTP
     * @return ResponseEntity com erro 400 (Bad Request)
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata exceções de validação de argumentos
     *
     * @param e exceção MethodArgumentNotValidException
     * @param request requisição HTTP
     * @return ResponseEntity com erro 422 (Unprocessable Entity) e detalhes dos campos inválidos
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Payload inválido!", request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata exceções de entidade não encontrada (JPA)
     *
     * @param e exceção EntityNotFoundException
     * @param request requisição HTTP
     * @return ResponseEntity com erro 422 (Unprocessable Entity)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Trata exceções de conteúdo vazio
     *
     * @param e exceção EmptyContent
     * @param request requisição HTTP
     * @return ResponseEntity com status 202 (Accepted)
     */
    @ExceptionHandler(EmptyContent.class)
    public ResponseEntity<CustomErrorDTO> emptyContent(EmptyContent e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.ACCEPTED;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
