package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidade que representa um tipo de combustível no sistema.
 * Cada combustível possui um nome único e um preço por litro.
 * Pode estar associado a múltiplas bombas de combustível.
 */
@Entity
@Table(name = "tb_combustivel")
public class Combustivel {

    /** Identificador único do combustível */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome do combustível (ex: GASOLINA, ETANOL) - deve ser único */
    @Column(unique = true)
    private String nomeCombustivel;

    /** Preço por litro do combustível */
    private double precoPorLitro;

    /** Bombas que oferecem este combustível (relacionamento muitos-para-muitos) */
    @ManyToMany(mappedBy = "combustiveis")
    private Set<BombaDeCombustivel> bombas = new HashSet<>();

    /** Construtor padrão */
    public Combustivel() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param nomeCombustivel nome do combustível
     * @param precoPorLitro preço por litro
     */
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
