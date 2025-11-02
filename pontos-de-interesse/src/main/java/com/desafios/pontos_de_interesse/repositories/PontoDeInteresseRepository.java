package com.desafios.pontos_de_interesse.repositories;

import com.desafios.pontos_de_interesse.entities.PontoDeInteresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoDeInteresseRepository extends JpaRepository<PontoDeInteresse, Long> {
}
