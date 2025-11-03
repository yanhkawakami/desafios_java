package com.desafios.transacoes.controllers;


import com.desafios.transacoes.dto.TransacaoDTO;
import com.desafios.transacoes.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService service;

    @PostMapping
    public ResponseEntity<TransacaoDTO> insert(@Valid @RequestBody TransacaoDTO transacaoDto){
        service.insert(transacaoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(transacaoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(null);
    }

}
