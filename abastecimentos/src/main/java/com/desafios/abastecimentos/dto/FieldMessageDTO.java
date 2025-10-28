package com.desafios.abastecimentos.dto;

/**
 * DTO para representar erros específicos de campo durante validação.
 * Utilizado pelo ValidationErrorDTO para detalhar quais campos
 * falharam na validação e suas respectivas mensagens.
 */
public class FieldMessageDTO {

    /** Nome do campo que falhou na validação */
    private String fieldName;

    /** Mensagem de erro específica do campo */
    private String message;

    /**
     * Construtor com parâmetros
     *
     * @param fieldName nome do campo
     * @param message mensagem de erro
     */
    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}