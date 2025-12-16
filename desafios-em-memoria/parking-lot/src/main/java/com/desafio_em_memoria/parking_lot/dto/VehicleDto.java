// java
package com.desafio_em_memoria.parking_lot.dto;

public class VehicleDto {
    private String placa;
    private String tipo;

    public VehicleDto() {}

    public VehicleDto(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}