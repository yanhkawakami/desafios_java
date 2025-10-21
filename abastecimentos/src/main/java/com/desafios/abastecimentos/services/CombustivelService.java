package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.entities.Combustivel;
import com.desafios.abastecimentos.repositories.CombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.EntityNotFoundException;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
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
                () -> new ResourceNotFoundException("Client ID " + id + " not found"));
        return new CombustivelDTO(combustivel);
    }

    @Transactional(readOnly = true)
    public Page<CombustivelDTO> findAll (Pageable pageable){
        Page<Combustivel> result = repository.findAll(pageable);
        return result.map(x -> new CombustivelDTO(x));
    }

    @Transactional
    public CombustivelDTO insert (CombustivelDTO combustivelDto){
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
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    public void copyDtoToEntity (Combustivel combustivel, CombustivelDTO combustivelDto){
        combustivel.setNomeCombustivel(combustivelDto.getNomeCombustivel());
        combustivel.setPrecoPorLitro(combustivelDto.getPrecoPorLitro());
    }

}
