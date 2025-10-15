package com.desafios.abastecimentos.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "tb_bombas")
public class BombasDeCombustivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeBomba;

    @ManyToMany
    @JoinTable(name = "tb_bomba_combustivel",
            joinColumns = @JoinColumn(name = "bomba_id"),
            inverseJoinColumns = @JoinColumn(name = "combustivel_id"))
    private Set<Combustiveis> combustiveis = new HashSet<>();

    @OneToMany(mappedBy = "bomba")
    private List<Abastecimentos> abastecimentos = new ArrayList<>();


    public BombasDeCombustivel(Long id, String nomeBomba) {
        this.id = id;
        this.nomeBomba = nomeBomba;
    }

    public Long getId() {
        return id;
    }

    public String getNomeBomba() {
        return nomeBomba;
    }

    public Set<Combustiveis> getCombustiveis() {
        return combustiveis;
    }
}
