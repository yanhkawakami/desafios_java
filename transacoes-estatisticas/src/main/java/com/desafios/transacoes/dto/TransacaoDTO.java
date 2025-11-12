package com.desafios.transacoes.dto;

import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;

public class TransacaoDTO {

    private Long id;
    @NotNull (message = "O campo 'valor' não pode ser nulo")
    @Positive (message = "O campo 'valor' precisa ser positivo")
    private float valor;

    @NotNull (message = "O campo 'dataHora' não pode ser nulo")
    @Past (message = "O campo 'dataHora' precisa estar no passado")
    private OffsetDateTime dataHora;

    public TransacaoDTO(Long id, float valor, OffsetDateTime dataHora) {
        this.id = id;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
