package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_abastecimentos")
public class Abastecimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "bomba_id")
    private BombasDeCombustivel bomba;

    private LocalDate data;
    private Double valor;
    private Double litragem;

    public Abastecimentos(Long id, BombasDeCombustivel bomba, LocalDate data, Double valor, Double litragem) {
        this.id = id;
        this.bomba = bomba;
        this.data = data;
        this.valor = valor;
        this.litragem = litragem;
    }

    public Long getId() {
        return id;
    }

    public BombasDeCombustivel getBomba() {
        return bomba;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getValor() {
        return valor;
    }

    public Double getLitragem() {
        return litragem;
    }
}
