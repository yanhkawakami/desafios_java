package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entidade que representa uma bomba de combustível no posto.
 * Cada bomba possui um nome único e pode oferecer múltiplos tipos de combustível.
 * Registra todos os abastecimentos realizados nela.
 */
@Entity
@Table(name = "tb_bomba")
public class BombaDeCombustivel {

    /** Identificador único da bomba */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome da bomba (ex: Bomba 1, Bomba 2) - deve ser único */
    @Column(unique = true)
    private String nomeBomba;

    /** Combustíveis oferecidos por esta bomba (relacionamento muitos-para-muitos) */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_bomba_combustivel",
            joinColumns = @JoinColumn(name = "bomba_id"),
            inverseJoinColumns = @JoinColumn(name = "combustivel_id")
    )
    private Set<Combustivel> combustiveis = new HashSet<>();

    /** Lista de abastecimentos realizados nesta bomba (relacionamento um-para-muitos) */
    @OneToMany(mappedBy = "bomba")
    private List<Abastecimento> abastecimentos = new ArrayList<>();

    /** Construtor padrão */
    public BombaDeCombustivel() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param nomeBomba nome da bomba
     */
    public BombaDeCombustivel(Long id, String nomeBomba) {
        this.id = id;
        this.nomeBomba = nomeBomba;
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

    public Set<Combustivel> getCombustiveis() {
        return combustiveis;
    }

    public void setCombustiveis(Set<Combustivel> combustiveis) {
        this.combustiveis = combustiveis;
    }

    public List<Abastecimento> getAbastecimentos() {
        return abastecimentos;
    }

    public void setAbastecimentos(List<Abastecimento> abastecimentos) {
        this.abastecimentos = abastecimentos;
    }
}
