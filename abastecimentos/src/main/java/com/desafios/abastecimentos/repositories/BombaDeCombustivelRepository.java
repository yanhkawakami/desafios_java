package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BombaDeCombustivelRepository extends JpaRepository<BombaDeCombustivel, Long> {
    Boolean existsByNomeBomba(String nomeBomba);

    @Query("SELECT b FROM BombaDeCombustivel b WHERE b.nomeBomba = :nomeBomba")
    BombaDeCombustivel findByNomeBomba(String nomeBomba);
}
