package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "tb_bomba")
public class BombaDeCombustivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeBomba;

    @ManyToMany
    @JoinTable(
            name = "tb_bomba_combustivel",
            joinColumns = @JoinColumn(name = "bomba_id"),
            inverseJoinColumns = @JoinColumn(name = "combustivel_id")
    )
    private Set<Combustivel> combustiveis = new HashSet<>();


    @OneToMany(mappedBy = "bomba")
    private List<Abastecimento> abastecimentos = new ArrayList<>();


    public BombaDeCombustivel(Long id, String nomeBomba) {
        this.id = id;
        this.nomeBomba = nomeBomba;
    }

    public Long getId() {
        return id;
    }

    public String getNomeBomba() {
        return nomeBomba;
    }

    public Set<Combustivel> getCombustiveis() {
        return combustiveis;
    }
}
