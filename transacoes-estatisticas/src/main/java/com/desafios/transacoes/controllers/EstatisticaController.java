package com.desafios.transacoes.controllers;

import com.desafios.transacoes.dto.TransacaoEstatisticaDTO;
import com.desafios.transacoes.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;

@RestController
@RequestMapping(value = "/estatistica")
public class EstatisticaController {

    @Autowired
    TransacaoService service;

    @GetMapping
    public ResponseEntity<TransacaoEstatisticaDTO> getEstatistica(
            @RequestParam (name = "janelaSegundos", defaultValue = "60") String janelaSegundos) {
        TransacaoEstatisticaDTO dto = service.getEstatistica(janelaSegundos);
        return ResponseEntity.ok(dto);
    }
}
