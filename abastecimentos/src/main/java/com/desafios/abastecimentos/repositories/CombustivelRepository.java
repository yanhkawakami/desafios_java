package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {

    // @Query("SELECT c FROM Combustivel c WHERE c.nomeCombustivel = :nomeCombustivel")
    Combustivel findByNomeCombustivel(String nomeCombustivel);
    Boolean existsByNomeCombustivel(String nomeCombustivel);
}
