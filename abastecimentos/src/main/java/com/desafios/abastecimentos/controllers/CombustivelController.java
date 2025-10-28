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

/**
 * Controller REST para gerenciamento de combustíveis.
 * Fornece endpoints para operações CRUD (Create, Read, Update, Delete)
 * na entidade Combustivel.
 */
@RestController
@RequestMapping(value = "/combustivel")
public class CombustivelController {

    @Autowired
    private CombustivelService service;

    /**
     * Busca um combustível pelo ID
     *
     * @param id identificador único do combustível
     * @return ResponseEntity contendo o combustível encontrado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CombustivelDTO> findById(@PathVariable Long id) {
        CombustivelDTO combustivelDto = service.findById(id);
        return ResponseEntity.ok(combustivelDto);
    }

    /**
     * Lista todos os combustíveis com paginação
     *
     * @param pageable configuração de paginação
     * @return ResponseEntity contendo página de combustíveis
     */
    @GetMapping
    public ResponseEntity<Page<CombustivelDTO>> findAll(Pageable pageable) {
        Page<CombustivelDTO> combustiveisDTO = service.findAll(pageable);
        return ResponseEntity.ok(combustiveisDTO);
    }

    /**
     * Cria um novo combustível
     *
     * @param combustivelDto dados do combustível a ser criado
     * @return ResponseEntity contendo o combustível criado e URI de localização
     */
    @PostMapping
    public ResponseEntity<CombustivelDTO> insert(@Valid @RequestBody CombustivelDTO combustivelDto) {
        CombustivelDTO dto = service.insert(combustivelDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Atualiza um combustível existente
     *
     * @param id identificador único do combustível
     * @param combustivelDto novos dados do combustível
     * @return ResponseEntity contendo o combustível atualizado
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<CombustivelDTO> update(@PathVariable Long id, @Valid @RequestBody CombustivelDTO combustivelDto) {
        combustivelDto = service.update(id, combustivelDto);
        return ResponseEntity.ok(combustivelDto);
    }

    /**
     * Remove um combustível pelo ID
     *
     * @param id identificador único do combustível
     * @return ResponseEntity com status 204 (No Content)
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
