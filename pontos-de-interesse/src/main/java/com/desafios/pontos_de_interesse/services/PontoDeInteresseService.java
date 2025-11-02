package com.desafios.pontos_de_interesse.services;

import com.desafios.pontos_de_interesse.dto.PontoDeInteresseMinDTO;
import com.desafios.pontos_de_interesse.repositories.PontoDeInteresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PontoDeInteresseService {

    @Autowired
    PontoDeInteresseRepository repository;

    public Page<PontoDeInteresseMinDTO> retrievePontosDeInteresse(Integer x, Integer y, Integer distanciaMaxima, Pageable pageable){
        return repository.retrievePontosDeInteresse(x, y, distanciaMaxima, pageable);
    }

}
