package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.entities.Combustivel;
import com.desafios.abastecimentos.repositories.CombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.EmptyContent;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada aos combustíveis.
 * Implementa operações CRUD e regras de negócio específicas.
 */
@Service
public class CombustivelService {

    @Autowired
    private CombustivelRepository repository;

    /**
     * Busca um combustível pelo ID
     *
     * @param id identificador único do combustível
     * @return DTO do combustível encontrado
     * @throws ResourceNotFoundException se o combustível não for encontrado
     */
    @Transactional(readOnly = true)
    public CombustivelDTO findById(Long id) {
        Optional<Combustivel> result = repository.findById(id);
        Combustivel combustivel = result.orElseThrow(
                () -> new ResourceNotFoundException("Combustível com ID " + id + " não encontrado"));
        return new CombustivelDTO(combustivel);
    }

    /**
     * Lista todos os combustíveis com paginação
     *
     * @param pageable configuração de paginação
     * @return página de DTOs de combustíveis
     * @throws EmptyContent se não houver combustíveis cadastrados
     */
    @Transactional(readOnly = true)
    public Page<CombustivelDTO> findAll(Pageable pageable) {
        Page<Combustivel> result = repository.findAll(pageable);
        if (!result.getContent().isEmpty()) {
            return result.map(CombustivelDTO::new);
        }
        throw new EmptyContent("Não há combustíveis cadastrados");
    }

    /**
     * Cria um novo combustível
     *
     * @param combustivelDto dados do combustível a ser criado
     * @return DTO do combustível criado
     * @throws DatabaseException se já existir um combustível com o mesmo nome
     */
    @Transactional
    public CombustivelDTO insert(CombustivelDTO combustivelDto) {
        if (repository.existsByNomeCombustivel(combustivelDto.getNomeCombustivel())) {
            throw new DatabaseException("Erro de integridade referencial: você está tentando criar um combustível que já existe");
        }
        Combustivel combustivel = new Combustivel();
        copyDtoToEntity(combustivel, combustivelDto);
        combustivel = repository.save(combustivel);
        return new CombustivelDTO(combustivel);
    }

    /**
     * Atualiza um combustível existente
     *
     * @param id identificador único do combustível
     * @param combustivelDto novos dados do combustível
     * @return DTO do combustível atualizado
     * @throws ResourceNotFoundException se o combustível não for encontrado
     */
    @Transactional
    public CombustivelDTO update(Long id, CombustivelDTO combustivelDto) {
        try {
            Combustivel combustivel = repository.getReferenceById(id);
            copyDtoToEntity(combustivel, combustivelDto);
            combustivel = repository.save(combustivel);
            return new CombustivelDTO(combustivel);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            throw new ResourceNotFoundException("Combustível com ID " + id + " não encontrado");
        }
    }

    /**
     * Remove um combustível pelo ID
     *
     * @param id identificador único do combustível
     * @throws ResourceNotFoundException se o combustível não for encontrado
     * @throws DatabaseException se houver violação de integridade referencial
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Combustível com ID " + id + " não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade referencial: você está tentando deletar um combustível relacionado");
        }
    }

    /**
     * Copia dados do DTO para a entidade
     *
     * @param combustivel entidade destino
     * @param combustivelDto DTO origem
     */
    private void copyDtoToEntity(Combustivel combustivel, CombustivelDTO combustivelDto) {
        combustivel.setNomeCombustivel(combustivelDto.getNomeCombustivel());
        combustivel.setPrecoPorLitro(combustivelDto.getPrecoPorLitro());
    }
}
