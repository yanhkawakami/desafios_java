package com.desafios.abastecimentos.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO especializado para erros de validação.
 * Estende CustomErrorDTO adicionando uma lista detalhada
 * dos campos que falharam na validação.
 */
public class ValidationErrorDTO extends CustomErrorDTO {

    /** Lista de erros específicos por campo */
    private List<FieldMessageDTO> errors = new ArrayList<>();

    /**
     * Construtor com parâmetros
     *
     * @param timestamp data e hora do erro
     * @param status código de status HTTP
     * @param error mensagem de erro
     * @param path caminho da requisição
     */
    public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDTO> getErrors() {
        return errors;
    }

    /**
     * Adiciona um erro específico de campo à lista
     *
     * @param fieldName nome do campo com erro
     * @param message mensagem de erro
     */
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessageDTO(fieldName, message));
    }
}
