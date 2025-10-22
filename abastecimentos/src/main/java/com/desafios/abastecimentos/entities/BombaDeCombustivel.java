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
    @Column(unique = true)
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

    public BombaDeCombustivel(){}

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
