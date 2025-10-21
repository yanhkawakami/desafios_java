package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.services.CombustivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/combustiveis")
public class CombustivelController {

    @Autowired
    CombustivelService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CombustivelDTO> findById(@PathVariable Long id){
        CombustivelDTO combustivelDTO = service.findById(id);
        return ResponseEntity.ok(combustivelDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CombustivelDTO>> findAll(Pageable pageable){
        Page<CombustivelDTO> combustiveisDTO = service.findAll(pageable);
        return ResponseEntity.ok(combustiveisDTO);
    }

    @PostMapping
    public ResponseEntity<CombustivelDTO> insert(@Valid @RequestBody CombustivelDTO combustivelDTO){
        CombustivelDTO dto = service.insert(combustivelDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<CombustivelDTO> update (@PathVariable Long id, @Valid @RequestBody CombustivelDTO combustivelDto){
        combustivelDto = service.update(id, combustivelDto);
        return ResponseEntity.ok(combustivelDto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
