package com.desafios.abastecimentos.dto;

import java.time.Instant;

/**
 * DTO para resposta de erro customizada.
 * Utilizado pelo ControllerExceptionHandler para padronizar
 * as respostas de erro da API.
 */
public class CustomErrorDTO {

    /** Data e hora do erro */
    private Instant timestamp;

    /** Código de status HTTP */
    private Integer status;

    /** Mensagem de erro */
    private String error;

    /** Caminho da requisição que gerou o erro */
    private String path;

    /**
     * Construtor com parâmetros
     *
     * @param timestamp data e hora do erro
     * @param status código de status HTTP
     * @param error mensagem de erro
     * @param path caminho da requisição
     */
    public CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
