package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "tb_combustiveis")
public class Combustiveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCombustivel;
    private double precoPorLitro;

    @ManyToMany(mappedBy = "combustiveis")
    private Set<BombasDeCombustivel> bombas = new HashSet<>();

    public Combustiveis(Long id, String nomeCombustivel, double precoPorLitro) {
        this.id = id;
        this.nomeCombustivel = nomeCombustivel;
        this.precoPorLitro = precoPorLitro;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCombustivel() {
        return nomeCombustivel;
    }

    public double getPrecoPorLitro() {
        return precoPorLitro;
    }

    public Set<BombasDeCombustivel> getBombas() {
        return bombas;
    }
}
