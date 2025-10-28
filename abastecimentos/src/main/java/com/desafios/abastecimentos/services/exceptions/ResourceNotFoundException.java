package com.desafios.abastecimentos.services.exceptions;

/**
 * Exceção customizada para recursos não encontrados.
 * Utilizada quando uma entidade solicitada não existe no banco de dados.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor com mensagem de erro
     *
     * @param message mensagem descritiva do erro
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
