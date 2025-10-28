package com.desafios.abastecimentos.services.exceptions;

/**
 * Exceção customizada para conteúdo vazio.
 * Utilizada quando uma consulta não retorna resultados,
 * mas isso não é considerado um erro crítico.
 */
public class EmptyContent extends RuntimeException {

    /**
     * Construtor com mensagem de erro
     *
     * @param message mensagem descritiva da situação
     */
    public EmptyContent(String message) {
        super(message);
    }
}
