package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um registro de abastecimento no posto.
 * Cada abastecimento está associado a uma bomba específica e registra
 * informações como data, valor total e quantidade em litros.
 */
@Entity
@Table(name = "tb_abastecimento")
public class Abastecimento {

    /** Identificador único do abastecimento */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Bomba onde foi realizado o abastecimento (relacionamento muitos-para-um) */
    @ManyToOne
    @JoinColumn(name = "bomba_id")
    private BombaDeCombustivel bomba;

    /** Data em que o abastecimento foi realizado */
    private LocalDate data;

    /** Valor total pago pelo abastecimento */
    private Double valor;

    /** Quantidade de combustível em litros */
    private Double litragem;

    /** Construtor padrão */
    public Abastecimento() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param bomba bomba utilizada
     * @param data data do abastecimento
     * @param valor valor total
     * @param litragem quantidade em litros
     */
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
