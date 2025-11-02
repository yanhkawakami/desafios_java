package com.desafios.pontos_de_interesse.repositories;

import com.desafios.pontos_de_interesse.dto.PontoDeInteresseMinDTO;
import com.desafios.pontos_de_interesse.entities.PontoDeInteresse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PontoDeInteresseRepository extends JpaRepository<PontoDeInteresse, Long> {

    @Query("SELECT new com.desafios.pontos_de_interesse.dto.PontoDeInteresseMinDTO(obj.nome, obj.x, obj.y) " +
            "FROM PontoDeInteresse obj " +
            "WHERE POWER(obj.x - :x, 2) + POWER(obj.y - :y, 2) <= :distanciaMaxima * :distanciaMaxima")
    Page<PontoDeInteresseMinDTO> retrievePontosDeInteresse(Integer x, Integer y, Integer distanciaMaxima, Pageable pageable);
}
