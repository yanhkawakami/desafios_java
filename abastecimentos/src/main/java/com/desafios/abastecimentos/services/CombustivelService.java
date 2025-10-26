package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.entities.Combustivel;
import com.desafios.abastecimentos.repositories.CombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.EmptyContent;
import com.desafios.abastecimentos.services.exceptions.EntityNotFoundException;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CombustivelService {

    @Autowired
    CombustivelRepository repository;

    @Transactional(readOnly = true)
    public CombustivelDTO findById (Long id){
        Optional<Combustivel> result = repository.findById(id);
        Combustivel combustivel = result.orElseThrow(
                () -> new ResourceNotFoundException("Combustível com ID " + id + " não encontrada"));
        return new CombustivelDTO(combustivel);
    }

    @Transactional(readOnly = true)
    public Page<CombustivelDTO> findAll (Pageable pageable){
        Page<Combustivel> result = repository.findAll(pageable);
        if (result.getContent().size() != 0) {
            return result.map(x -> new CombustivelDTO(x));
        }
        throw new EmptyContent("Não há combustíveis cadastrados");
    }

    @Transactional
    public CombustivelDTO insert (CombustivelDTO combustivelDto){
        if (repository.existsByNomeCombustivel(combustivelDto.getNomeCombustivel())){
            throw new DatabaseException("Erro de integridade referencial: você está tentando criar um combustível que já existe");
        }
        Combustivel combustivel = new Combustivel();
        copyDtoToEntity(combustivel, combustivelDto);
        combustivel = repository.save(combustivel);
        return new CombustivelDTO(combustivel);

    }

    @Transactional
    public CombustivelDTO update(Long id, CombustivelDTO combustivelDto){
        try {
            Combustivel combustivel = repository.getReferenceById(id);
            copyDtoToEntity(combustivel, combustivelDto);
            combustivel = repository.save(combustivel);
            return new CombustivelDTO(combustivel);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Combustível com ID " + id + " não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Combustível com ID " + id + " não encontrada");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade referencial: você está tentando deletar um combustíivel relacionado");
        }
    }

    public void copyDtoToEntity (Combustivel combustivel, CombustivelDTO combustivelDto) {
        combustivel.setNomeCombustivel(combustivelDto.getNomeCombustivel());
        combustivel.setPrecoPorLitro(combustivelDto.getPrecoPorLitro());
    }

}
