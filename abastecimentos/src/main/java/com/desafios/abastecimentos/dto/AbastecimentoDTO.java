package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Abastecimento;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AbastecimentoDTO {
    private Long id;
    @NotBlank(message = "O campo 'bomba' não pode ser nulo")
    private String nomeBomba;
    @NotBlank(message = "O campo 'data' não pode ser nulo")
    private LocalDate data;
    @NotBlank(message = "O campo 'valor' não pode ser nulo")
    private Double valor;
    @NotBlank(message = "O campo 'litragem' não pode ser nulo")
    private Double litragem;

    public AbastecimentoDTO() {}

    public AbastecimentoDTO(Long id, String nomeBomba, LocalDate data, Double valor, Double litragem) {
        this.id = id;
        this.nomeBomba = nomeBomba;
        this.data = data;
        this.valor = valor;
        this.litragem = litragem;
    }

    public AbastecimentoDTO(Abastecimento abastecimento){
        id = abastecimento.getId();
        nomeBomba = abastecimento.getBomba().getNomeBomba();
        data = abastecimento.getData();
        valor = abastecimento.getValor();
        litragem = abastecimento.getLitragem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBomba() {
        return nomeBomba;
    }

    public void setNomeBomba(String nomeBomba) {
        this.nomeBomba = nomeBomba;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getLitragem() {
        return litragem;
    }

    public void setLitragem(Double litragem) {
        this.litragem = litragem;
    }
}
