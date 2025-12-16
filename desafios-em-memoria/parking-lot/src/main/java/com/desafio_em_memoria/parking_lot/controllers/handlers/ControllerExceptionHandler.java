package com.desafio_em_memoria.parking_lot.controllers.handlers;

import com.desafio_em_memoria.parking_lot.services.exceptions.NoAvailableSlotsException;
import com.desafio_em_memoria.parking_lot.services.exceptions.VehicleDoesNotExistException;
import com.desafio_em_memoria.parking_lot.dto.CustomErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(VehicleDoesNotExistException.class)
    public ResponseEntity<CustomErrorDTO> vehicleDoesNotExist(VehicleDoesNotExistException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoAvailableSlotsException.class)
    public ResponseEntity<CustomErrorDTO> noSlotsAvailabe(NoAvailableSlotsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}