package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.controllers.CombustiveisController;
import com.desafios.abastecimentos.dto.CombustiveisDTO;
import com.desafios.abastecimentos.entities.Combustiveis;
import com.desafios.abastecimentos.repositories.CombustiveisRepository;
import com.desafios.abastecimentos.services.exceptions.EntityNotFoundException;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CombustiveisService {

    @Autowired
    CombustiveisRepository repository;

    @Transactional(readOnly = true)
    public CombustiveisDTO findById (Long id){
        Optional<Combustiveis> result = repository.findById(id);
        Combustiveis combustiveis = result.orElseThrow(
                () -> new ResourceNotFoundException("Client ID " + id + " not found"));
        return new CombustiveisDTO(combustiveis);
    }

    @Transactional(readOnly = true)
    public Page<CombustiveisDTO> findAll (Pageable pageable){
        Page<Combustiveis> result = repository.findAll(pageable);
        return result.map(x -> new CombustiveisDTO(x));
    }

    @Transactional
    public CombustiveisDTO insert (CombustiveisDTO combustiveisDto){
        Combustiveis combustiveis = new Combustiveis();
        copyDtoToEntity(combustiveis, combustiveisDto);
        combustiveis = repository.save(combustiveis);
        return new CombustiveisDTO(combustiveis);
    }

    @Transactional
    public CombustiveisDTO update(Long id, CombustiveisDTO combustiveisDto){
        try {
            Combustiveis combustiveis = repository.getReferenceById(id);
            copyDtoToEntity(combustiveis, combustiveisDto);
            combustiveis = repository.save(combustiveis);
            return new CombustiveisDTO(combustiveis);
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

    public void copyDtoToEntity (Combustiveis combustiveis, CombustiveisDTO combustiveisDto){
        combustiveis.setNomeCombustivel(combustiveisDto.getNomeCombustivel());
        combustiveis.setPrecoPorLitro(combustiveisDto.getPrecoPorLitro());
    }

}
