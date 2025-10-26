package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Abastecimento;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import jakarta.persistence.*;

import java.time.LocalDate;

public class AbastecimentoDTO {
    private Long id;
    private BombaDeCombustivel bomba;
    private LocalDate data;
    private Double valor;
    private Double litragem;

    public AbastecimentoDTO() {}

    public AbastecimentoDTO(Long id, BombaDeCombustivel bomba, LocalDate data, Double valor, Double litragem) {
        this.id = id;
        this.bomba = bomba;
        this.data = data;
        this.valor = valor;
        this.litragem = litragem;
    }

    public AbastecimentoDTO(Abastecimento abastecimento){
        id = abastecimento.getId();;
        bomba = abastecimento.getBomba();
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

    public BombaDeCombustivel getBomba() {
        return bomba;
    }

    public void setBomba(BombaDeCombustivel bomba) {
        this.bomba = bomba;
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
