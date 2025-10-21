package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Combustiveis;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CombustiveisDTO {
    private Long id;
    @NotBlank(message = "The field 'nomeCombustivel' cannot be null")
    private String nomeCombustivel;
    @NotNull(message = "The field 'precoPorLitro' cannot be null")
    @PositiveOrZero (message = "The field 'precoPorLitro' cannot be negative or zero")
    private double precoPorLitro;

    public CombustiveisDTO() {}

    public CombustiveisDTO(Long id, String nomeCombustivel, double precoPorLitro) {
        this.id = id;
        this.nomeCombustivel = nomeCombustivel;
        this.precoPorLitro = precoPorLitro;
    }

    public CombustiveisDTO(Combustiveis combustiveis){
        id = combustiveis.getId();;
        nomeCombustivel = combustiveis.getNomeCombustivel();
        precoPorLitro = combustiveis.getPrecoPorLitro();
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
}
