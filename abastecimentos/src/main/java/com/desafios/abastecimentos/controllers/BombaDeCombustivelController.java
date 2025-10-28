package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.services.BombaDeCombustivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller REST para gerenciamento de bombas de combustível.
 * Fornece endpoints para operações CRUD (Create, Read, Update, Delete)
 * na entidade BombaDeCombustivel.
 */
@RestController
@RequestMapping(value = "/bomba-de-combustivel")
public class BombaDeCombustivelController {

    @Autowired
    private BombaDeCombustivelService service;

    /**
     * Busca uma bomba de combustível pelo ID
     *
     * @param id identificador único da bomba
     * @return ResponseEntity contendo a bomba encontrada
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<BombaDeCombustivelDTO> findById(@PathVariable Long id) {
        BombaDeCombustivelDTO bombaDeCombustivelDto = service.findById(id);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    /**
     * Lista todas as bombas de combustível com paginação
     *
     * @param pageable configuração de paginação
     * @return ResponseEntity contendo página de bombas
     */
    @GetMapping
    public ResponseEntity<Page<BombaDeCombustivelDTO>> findAll(Pageable pageable) {
        Page<BombaDeCombustivelDTO> bombaDeCombustivelDto = service.findAll(pageable);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    /**
     * Cria uma nova bomba de combustível
     *
     * @param bombaDeCombustivelDto dados da bomba a ser criada
     * @return ResponseEntity contendo a bomba criada e URI de localização
     */
    @PostMapping
    public ResponseEntity<BombaDeCombustivelDTO> insert(@Valid @RequestBody BombaDeCombustivelDTO bombaDeCombustivelDto) {
        BombaDeCombustivelDTO dto = service.insert(bombaDeCombustivelDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Atualiza uma bomba de combustível existente
     *
     * @param id identificador único da bomba
     * @param bombaDeCombustivelDto novos dados da bomba
     * @return ResponseEntity contendo a bomba atualizada
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<BombaDeCombustivelDTO> update(@PathVariable Long id, @Valid @RequestBody BombaDeCombustivelDTO bombaDeCombustivelDto) {
        bombaDeCombustivelDto = service.update(id, bombaDeCombustivelDto);
        return ResponseEntity.ok(bombaDeCombustivelDto);
    }

    /**
     * Remove uma bomba de combustível pelo ID
     *
     * @param id identificador único da bomba
     * @return ResponseEntity com status 204 (No Content)
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
