package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repositório para operações de acesso aos dados da entidade BombaDeCombustivel.
 * Estende JpaRepository fornecendo operações CRUD padrão e métodos customizados.
 */
public interface BombaDeCombustivelRepository extends JpaRepository<BombaDeCombustivel, Long> {

    /**
     * Verifica se existe uma bomba com o nome especificado
     *
     * @param nomeBomba nome da bomba
     * @return true se existir, false caso contrário
     */
    Boolean existsByNomeBomba(String nomeBomba);

    /**
     * Busca uma bomba pelo nome
     *
     * @param nomeBomba nome da bomba
     * @return bomba encontrada ou null
     */
    @Query("SELECT b FROM BombaDeCombustivel b WHERE b.nomeBomba = :nomeBomba")
    BombaDeCombustivel findByNomeBomba(String nomeBomba);
}
