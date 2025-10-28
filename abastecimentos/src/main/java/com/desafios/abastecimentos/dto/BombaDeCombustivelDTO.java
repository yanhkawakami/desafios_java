package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO (Data Transfer Object) para transferência de dados de bomba de combustível.
 * Utilizado nas operações da API REST para criar, atualizar e retornar
 * informações de bomba incluindo seus combustíveis associados.
 */
public class BombaDeCombustivelDTO {

    /** Identificador único da bomba */
    private Long id;

    /** Nome da bomba - obrigatório e não pode ser vazio */
    @NotBlank(message = "O campo 'nomeBomba' não pode ser nulo")
    private String nomeBomba;

    /** Lista de combustíveis oferecidos pela bomba */
    private List<CombustivelDTO> combustiveis = new ArrayList<>();

    /** Construtor padrão */
    public BombaDeCombustivelDTO() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param nomeBomba nome da bomba
     */
    public BombaDeCombustivelDTO(Long id, String nomeBomba) {
        this.id = id;
        this.nomeBomba = nomeBomba;
    }

    /**
     * Construtor que converte uma entidade BombaDeCombustivel em DTO
     *
     * @param bomba entidade bomba de combustível
     */
    public BombaDeCombustivelDTO(BombaDeCombustivel bomba) {
        id = bomba.getId();
        nomeBomba = bomba.getNomeBomba();
        for (Combustivel combustivel : bomba.getCombustiveis()) {
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
