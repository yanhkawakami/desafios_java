package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "tb_combustivel")
public class Combustivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeCombustivel;
    private double precoPorLitro;

    @ManyToMany (mappedBy = "combustiveis")
    private Set<BombaDeCombustivel> bombas = new HashSet<>();

    public Combustivel() {}

    public Combustivel(Long id, String nomeCombustivel, double precoPorLitro) {
        this.id = id;
        this.nomeCombustivel = nomeCombustivel;
        this.precoPorLitro = precoPorLitro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCombustivel() {
        return nomeCombustivel;
    }

    public void setNomeCombustivel(String nomeCombustivel) {
        this.nomeCombustivel = nomeCombustivel;
    }

    public double getPrecoPorLitro() {
        return precoPorLitro;
    }

    public void setPrecoPorLitro(double precoPorLitro) {
        this.precoPorLitro = precoPorLitro;
    }

    public Set<BombaDeCombustivel> getBombas() {
        return bombas;
    }
}
