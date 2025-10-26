package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.services.BombaDeCombustivelService;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/bomba-de-combustivel")
public class BombaDeCombustivelController {
    @Autowired
    BombaDeCombustivelService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BombaDeCombustivelDTO> findById(@PathVariable Long id){
        BombaDeCombustivelDTO bombaDeCombustivelDto = service.findById(id);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    @GetMapping
    public ResponseEntity<Page<BombaDeCombustivelDTO>> findAll(Pageable pageable){
        Page<BombaDeCombustivelDTO> bombaDeCombustivelDto = service.findAll(pageable);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    @PostMapping
    public ResponseEntity<BombaDeCombustivelDTO> insert(@Valid @RequestBody BombaDeCombustivelDTO bombaDeCombustivelDto){
        BombaDeCombustivelDTO dto = service.insert(bombaDeCombustivelDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<BombaDeCombustivelDTO> update (@PathVariable Long id, @Valid @RequestBody BombaDeCombustivelDTO bombaDeCombustivelDto){
        bombaDeCombustivelDto = service.update(id, bombaDeCombustivelDto);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
