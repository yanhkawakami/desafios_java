package com.desafio_em_memoria.parking_lot.services;

import com.desafio_em_memoria.parking_lot.dto.VehicleCountDTO;
import com.desafio_em_memoria.parking_lot.dto.VehicleDto;
import com.desafio_em_memoria.parking_lot.services.exceptions.NoAvailableSlotsException;
import com.desafio_em_memoria.parking_lot.services.exceptions.VehicleDoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingLotService {

    private Set<VehicleDto> slots = new HashSet<>();
    private Set<VehicleCountDTO> vehicleCount = new HashSet<>(
            Arrays.asList(
                    new VehicleCountDTO("CARRO", 0),
                    new VehicleCountDTO("MOTO", 0)
            )
    );

    public void parkVehicle(VehicleDto vehicle) {
        if (Objects.equals(vehicle.getTipo(), "CARRO")) {
            if (vehicleCount.stream().anyMatch(v -> v.getTipo().equals("CARRO") && v.getQuantidade() >= 10)) {
                throw new NoAvailableSlotsException("Não há vagas disponíveis para CARRO");
            }
        } else if (Objects.equals(vehicle.getTipo(), "MOTO")) {
            if (vehicleCount.stream().anyMatch(v -> v.getTipo().equals("MOTO") && v.getQuantidade() >= 5)) {
                throw new NoAvailableSlotsException("Não há vagas disponíveis para MOTO");
            }
        }
        slots.add(vehicle);
        updateVehicleCount(vehicle.getTipo(), 1);
    }

    public void removeVehicle(String placa) {
        Optional<VehicleDto> vehicleToRemove = slots.stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst();

        if (vehicleToRemove.isEmpty()) {
            throw new VehicleDoesNotExistException("Veículo com placa " + placa + " não encontrado no estacionamento");
        }

        updateVehicleCount(vehicleToRemove.get().getTipo(), -1);
        slots.remove(vehicleToRemove);
    }

    public Set<VehicleCountDTO> getSlots() {
        return vehicleCount;
    }

    private void updateVehicleCount(String tipo, Integer number){
        for (VehicleCountDTO countDTO : vehicleCount){
            if (countDTO.getTipo().equals(tipo)){
                countDTO.setQuantidade(countDTO.getQuantidade() + number);
                return;
            }
        }
    }
}
