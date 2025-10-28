package com.desafios.abastecimentos.repositories;

import com.desafios.abastecimentos.entities.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações de acesso aos dados da entidade Abastecimento.
 * Estende JpaRepository fornecendo operações CRUD padrão.
 */
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}