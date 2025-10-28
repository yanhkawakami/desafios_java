package com.desafios.abastecimentos.dto;

import com.desafios.abastecimentos.entities.Abastecimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para transferência de dados de abastecimento.
 * Utilizado nas operações da API REST para criar, atualizar e retornar
 * informações de abastecimento.
 */
public class AbastecimentoDTO {

    /** Identificador único do abastecimento */
    private Long id;

    /** Nome da bomba onde foi realizado o abastecimento - obrigatório */
    @NotBlank(message = "O campo 'bomba' não pode ser nulo")
    private String nomeBomba;

    /** Data do abastecimento - obrigatória */
    @NotNull(message = "O campo 'data' não pode ser nulo")
    private LocalDate data;

    /** Valor total do abastecimento - obrigatório */
    @NotNull(message = "O campo 'valor' não pode ser nulo")
    private Double valor;

    /** Quantidade de combustível em litros - obrigatória */
    @NotNull(message = "O campo 'litragem' não pode ser nulo")
    private Double litragem;

    /** Construtor padrão */
    public AbastecimentoDTO() {}

    /**
     * Construtor com parâmetros
     *
     * @param id identificador único
     * @param nomeBomba nome da bomba
     * @param data data do abastecimento
     * @param valor valor total
     * @param litragem quantidade em litros
     */
    public AbastecimentoDTO(Long id, String nomeBomba, LocalDate data, Double valor, Double litragem) {
        this.id = id;
        this.nomeBomba = nomeBomba;
        this.data = data;
        this.valor = valor;
        this.litragem = litragem;
    }

    /**
     * Construtor que converte uma entidade Abastecimento em DTO
     *
     * @param abastecimento entidade abastecimento
     */
    public AbastecimentoDTO(Abastecimento abastecimento) {
        id = abastecimento.getId();
        nomeBomba = abastecimento.getBomba().getNomeBomba();
        data = abastecimento.getData();
        valor = abastecimento.getValor();
        litragem = abastecimento.getLitragem();
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
