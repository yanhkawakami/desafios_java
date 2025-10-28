package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.services.AbastecimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    public ResponseEntity<AbastecimentoDTO> insert(@Valid @RequestBody AbastecimentoDTO abastecimentoDto){
        AbastecimentoDTO dto = service.insert(abastecimentoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<AbastecimentoDTO> update (@PathVariable Long id, @Valid @RequestBody AbastecimentoDTO abastecimentoDto){
        abastecimentoDto = service.update(id, abastecimentoDto);
        return ResponseEntity.ok(abastecimentoDto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
