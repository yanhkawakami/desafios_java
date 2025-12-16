package com.desafio_em_memoria.parking_lot.controllers;


import com.desafio_em_memoria.parking_lot.dto.VehicleCountDTO;
import com.desafio_em_memoria.parking_lot.dto.VehicleDto;
import com.desafio_em_memoria.parking_lot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/parking-lot")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/entrada")
    public ResponseEntity<String> parkVehicle(@RequestBody VehicleDto dto) {
        parkingLotService.parkVehicle(dto);
        return ResponseEntity.ok("Veículo estacionado com sucesso");
    }

    @DeleteMapping("/saida/{placa}")
    public ResponseEntity<String> removeVehicle(@PathVariable String placa) {
        parkingLotService.removeVehicle(placa);
        return ResponseEntity.ok("Veículo removido com sucesso");
    }

    @GetMapping("/vagas")
    public ResponseEntity<Set<VehicleCountDTO>> getSlots() {
        Set<VehicleCountDTO> slots = parkingLotService.getSlots();
        return ResponseEntity.ok(slots);
    }
}
