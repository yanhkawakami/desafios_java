package com.desafios.abastecimentos.services.exceptions;

/**
 * Exceção customizada para erros relacionados ao banco de dados.
 * Utilizada quando ocorrem violações de integridade referencial
 * ou outros problemas de persistência.
 */
public class DatabaseException extends RuntimeException {

    /**
     * Construtor com mensagem de erro
     *
     * @param message mensagem descritiva do erro
     */
    public DatabaseException(String message) {
        super(message);
    }
}
