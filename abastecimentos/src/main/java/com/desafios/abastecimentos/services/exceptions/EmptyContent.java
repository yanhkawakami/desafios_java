package com.desafios.abastecimentos.services.exceptions;

public class EmptyContent extends RuntimeException {
    public EmptyContent(String message) {
        super(message);
    }
}
