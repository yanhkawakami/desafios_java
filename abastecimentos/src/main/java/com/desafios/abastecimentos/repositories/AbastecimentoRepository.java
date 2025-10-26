package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}