package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_abastecimento")
public class Abastecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "bomba_id")
    private BombaDeCombustivel bomba;


    private LocalDate data;
    private Double valor;
    private Double litragem;

    public Abastecimento() {}

    public Abastecimento(Long id, BombaDeCombustivel bomba, LocalDate data, Double valor, Double litragem) {
        this.id = id;
        this.bomba = bomba;
        this.data = data;
        this.valor = valor;
        this.litragem = litragem;
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
