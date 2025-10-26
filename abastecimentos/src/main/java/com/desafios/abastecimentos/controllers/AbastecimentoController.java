package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.services.AbastecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/abastecimento")
public class AbastecimentoController {

    @Autowired
    AbastecimentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AbastecimentoDTO> findById(@PathVariable Long id){
        AbastecimentoDTO abastecimentoDto = service.findById(id);
        return ResponseEntity.ok(abastecimentoDto);
    }

    @GetMapping
    public ResponseEntity<Page<AbastecimentoDTO>> findAll(Pageable pageable){
        Page<AbastecimentoDTO> abastecimentoDto = service.findAll(pageable);
        return ResponseEntity.ok(abastecimentoDto);
    }

}
