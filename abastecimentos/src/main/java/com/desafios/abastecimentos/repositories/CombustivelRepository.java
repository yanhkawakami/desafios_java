package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações de acesso aos dados da entidade Combustivel.
 * Estende JpaRepository fornecendo operações CRUD padrão e métodos customizados.
 */
public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {

    /**
     * Busca um combustível pelo nome
     *
     * @param nomeCombustivel nome do combustível
     * @return combustível encontrado ou null
     */
    Combustivel findByNomeCombustivel(String nomeCombustivel);

    /**
     * Verifica se existe um combustível com o nome especificado
     *
     * @param nomeCombustivel nome do combustível
     * @return true se existir, false caso contrário
     */
    Boolean existsByNomeCombustivel(String nomeCombustivel);
}
