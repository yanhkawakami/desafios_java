package com.desafios.abastecimentos.controllers;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.services.AbastecimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller REST para gerenciamento de abastecimentos.
 * Fornece endpoints para operações CRUD (Create, Read, Update, Delete)
 * na entidade Abastecimento.
 */
@RestController
@RequestMapping(value = "/abastecimento")
public class AbastecimentoController {

    @Autowired
    private AbastecimentoService service;

    /**
     * Busca um abastecimento pelo ID
     *
     * @param id identificador único do abastecimento
     * @return ResponseEntity contendo o abastecimento encontrado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<AbastecimentoDTO> findById(@PathVariable Long id) {
        AbastecimentoDTO abastecimentoDto = service.findById(id);
        return ResponseEntity.ok(abastecimentoDto);
    }

    /**
     * Lista todos os abastecimentos com paginação
     *
     * @param pageable configuração de paginação
     * @return ResponseEntity contendo página de abastecimentos
     */
    @GetMapping
    public ResponseEntity<Page<AbastecimentoDTO>> findAll(Pageable pageable) {
        Page<AbastecimentoDTO> abastecimentoDto = service.findAll(pageable);
        return ResponseEntity.ok(abastecimentoDto);
    }

    /**
     * Registra um novo abastecimento
     *
     * @param abastecimentoDto dados do abastecimento a ser criado
     * @return ResponseEntity contendo o abastecimento criado e URI de localização
     */
    @PostMapping
    public ResponseEntity<AbastecimentoDTO> insert(@Valid @RequestBody AbastecimentoDTO abastecimentoDto) {
        AbastecimentoDTO dto = service.insert(abastecimentoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Atualiza um abastecimento existente
     *
     * @param id identificador único do abastecimento
     * @param abastecimentoDto novos dados do abastecimento
     * @return ResponseEntity contendo o abastecimento atualizado
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<AbastecimentoDTO> update(@PathVariable Long id, @Valid @RequestBody AbastecimentoDTO abastecimentoDto) {
        abastecimentoDto = service.update(id, abastecimentoDto);
        return ResponseEntity.ok(abastecimentoDto);
    }

    /**
     * Remove um abastecimento pelo ID
     *
     * @param id identificador único do abastecimento
     * @return ResponseEntity com status 204 (No Content)
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
