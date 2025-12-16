package com.desafio_em_memoria.parking_lot.services.exceptions;

public class VehicleDoesNotExistException extends RuntimeException {
    public VehicleDoesNotExistException(String message) {
        super(message);
    }
}
