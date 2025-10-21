package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.CombustiveisDTO;
import com.desafios.abastecimentos.services.CombustiveisService;
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
public class CombustiveisController {

    @Autowired
    CombustiveisService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CombustiveisDTO> findById(@PathVariable Long id){
        CombustiveisDTO combustiveisDTO = service.findById(id);
        return ResponseEntity.ok(combustiveisDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CombustiveisDTO>> findAll(Pageable pageable){
        Page<CombustiveisDTO> combustiveisDTO = service.findAll(pageable);
        return ResponseEntity.ok(combustiveisDTO);
    }

    @PostMapping
    public ResponseEntity<CombustiveisDTO> insert(@Valid @RequestBody CombustiveisDTO combustiveisDTO){
        CombustiveisDTO dto = service.insert(combustiveisDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<CombustiveisDTO> update (@PathVariable Long id, @Valid @RequestBody CombustiveisDTO combustiveisDto){
        combustiveisDto = service.update(id, combustiveisDto);
        return ResponseEntity.ok(combustiveisDto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
