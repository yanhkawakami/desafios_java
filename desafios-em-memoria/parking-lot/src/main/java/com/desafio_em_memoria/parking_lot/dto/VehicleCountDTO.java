package com.desafio_em_memoria.parking_lot.dto;

public class VehicleCountDTO {
    private String tipo;
    private int quantidade;

    public VehicleCountDTO() {}

    public VehicleCountDTO(String tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
