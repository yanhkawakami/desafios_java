package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BombaDeCombustivelDTO {
    private Long id;
    @NotBlank(message = "The field 'nomeBomba' cannot be null")
    private String nomeBomba;
    private List<CombustivelDTO> combustiveis = new ArrayList<>();

    public BombaDeCombustivelDTO() {}

    public BombaDeCombustivelDTO(Long id, String nomeBomba) {
        this.id = id;
        this.nomeBomba = nomeBomba;
    }

    public BombaDeCombustivelDTO(BombaDeCombustivel bomba){
        id = bomba.getId();
        nomeBomba = bomba.getNomeBomba();
        for (Combustivel combustivel : bomba.getCombustiveis()){
            combustiveis.add(new CombustivelDTO(combustivel));
        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeBomba() {
        return nomeBomba;
    }

    public List<CombustivelDTO> getCombustiveis() {
        return combustiveis;
    }
}
