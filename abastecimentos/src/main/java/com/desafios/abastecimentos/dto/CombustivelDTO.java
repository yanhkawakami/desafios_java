package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Combustivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.UniqueElements;

public class CombustivelDTO {
    private Long id;
    @NotBlank(message = "The field 'nomeCombustivel' cannot be null")
    private String nomeCombustivel;
    @NotNull(message = "The field 'precoPorLitro' cannot be null")
    @PositiveOrZero (message = "The field 'precoPorLitro' cannot be negative or zero")
    private double precoPorLitro;

    public CombustivelDTO() {}

    public CombustivelDTO(Long id, String nomeCombustivel, double precoPorLitro) {
        this.id = id;
        this.nomeCombustivel = nomeCombustivel;
        this.precoPorLitro = precoPorLitro;
    }

    public CombustivelDTO(Combustivel combustivel){
        id = combustivel.getId();
        nomeCombustivel = combustivel.getNomeCombustivel();
        precoPorLitro = combustivel.getPrecoPorLitro();
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
