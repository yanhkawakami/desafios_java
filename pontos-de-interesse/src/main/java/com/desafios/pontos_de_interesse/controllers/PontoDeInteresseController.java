package com.desafios.pontos_de_interesse.controllers;

import com.desafios.pontos_de_interesse.dto.PontoDeInteresseMinDTO;
import com.desafios.pontos_de_interesse.services.PontoDeInteresseService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/ponto-de-interesse")
@Validated
public class PontoDeInteresseController {

    @Autowired
    PontoDeInteresseService service;

    @GetMapping
    public ResponseEntity<Page<PontoDeInteresseMinDTO>> retrievePontosDeInteresse(
            @RequestParam(name = "x") @Min(value = 0, message = "O parâmetro x precisa ser maior ou igual a zero") Integer x,
            @RequestParam(name = "y") @Min(value = 0, message = "O parâmetro y precisa ser maior ou igual a zero") Integer y,
            @RequestParam(name = "distanciaMaxima") @Min(value = 0, message = "O parâmetro distanciaMaxima precisa ser maior ou igual a zero") Integer distanciaMaxima,
            Pageable pageable
    ){
        Page<PontoDeInteresseMinDTO> result = service.retrievePontosDeInteresse(x, y, distanciaMaxima, pageable);
        return ResponseEntity.ok(result);
    }

}
