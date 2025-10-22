package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long> {

    Combustivel findByNomeCombustivel(String nomeCombustivel);
    Boolean existsByNomeCombustivel(String nomeCombustivel);
}
