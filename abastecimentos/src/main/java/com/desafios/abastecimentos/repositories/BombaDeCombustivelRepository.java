package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BombaDeCombustivelRepository extends JpaRepository<BombaDeCombustivel, Long> {
}
