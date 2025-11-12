package com.desafios.transacoes.controllers.handlers;

import com.desafios.transacoes.dto.CustomErrorDTO;
import com.desafios.transacoes.dto.ValidationErrorDTO;
import com.desafios.transacoes.services.TransacaoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class TransacaoControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TransacaoControllerExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationErrors(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO err = new ValidationErrorDTO(Instant.now(), status.value(), "Dados de entrada inválidos", request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
            log.error("Problema na validação do campo: {}", f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorDTO> handleInvalidPayloadErrors(HttpMessageNotReadableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Payload inválido");
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), "O payload passado é inválido, consulte a documentação.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
