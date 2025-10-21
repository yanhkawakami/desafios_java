package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.Combustiveis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustiveisRepository extends JpaRepository<Combustiveis, Long> {
}
