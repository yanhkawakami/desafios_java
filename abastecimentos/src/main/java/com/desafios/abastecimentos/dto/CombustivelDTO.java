package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Combustivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO (Data Transfer Object) para transferência de dados de combustível.
 * Utilizado nas operações da API REST para criar, atualizar e retornar
 * informações de combustível.
 */
public class CombustivelDTO {

    /** Identificador único do combustível */
    private Long id;

    /** Nome do combustível - obrigatório e não pode ser vazio */
    @NotBlank(message = "O campo 'nomeCombustivel' não pode ser nulo")
    private String nomeCombustivel;

    /** Preço por litro - obrigatório e deve ser positivo ou zero */
    @NotNull(message = "O campo 'precoPorLitro' não pode ser nulo")
    @PositiveOrZero(message = "O campo 'precoPorLitro' não pode ser negativo ou zero")
    private double precoPorLitro;

    /** Construtor padrão */
    public CombustivelDTO() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param nomeCombustivel nome do combustível
     * @param precoPorLitro preço por litro
     */
    public CombustivelDTO(Long id, String nomeCombustivel, double precoPorLitro) {
        this.id = id;
        this.nomeCombustivel = nomeCombustivel;
        this.precoPorLitro = precoPorLitro;
    }

    /**
     * Construtor que converte uma entidade Combustivel em DTO
     *
     * @param combustivel entidade combustível
     */
    public CombustivelDTO(Combustivel combustivel) {
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
