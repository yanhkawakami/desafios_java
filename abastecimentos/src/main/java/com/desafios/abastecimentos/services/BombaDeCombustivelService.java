package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import com.desafios.abastecimentos.repositories.BombaDeCombustivelRepository;
import com.desafios.abastecimentos.repositories.CombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@Service
public class BombaDeCombustivelService {

    @Autowired
    BombaDeCombustivelRepository repository;

    @Autowired
    CombustivelRepository combustivelRepository;

    @Transactional(readOnly = true)
    public BombaDeCombustivelDTO findById(Long id) {
        Optional<BombaDeCombustivel> result = repository.findById(id);
        BombaDeCombustivel bomba = result.orElseThrow(
                () -> new ResourceNotFoundException("Client ID " + id + " not found"));
        return new BombaDeCombustivelDTO(bomba);
    }

    @Transactional(readOnly = true)
    public Page<BombaDeCombustivelDTO> findAll(Pageable pageable) {
        Page<BombaDeCombustivel> result = repository.findAll(pageable);
        return result.map(x -> new BombaDeCombustivelDTO(x));
    }

    @Transactional
    public BombaDeCombustivelDTO insert(BombaDeCombustivelDTO bombaDeCombustivelDto) {
        if (repository.existsByNomeBomba(bombaDeCombustivelDto.getNomeBomba())){
            throw new DatabaseException("Erro de integridade referencial: você está tentando criar uma bomba de combustível que já existe");
        }
        BombaDeCombustivel bomba = new BombaDeCombustivel();
        copyDtoToEntity(bomba, bombaDeCombustivelDto);
        bomba = repository.save(bomba);
        return new BombaDeCombustivelDTO(bomba);
    }

    public void copyDtoToEntity(BombaDeCombustivel bomba, BombaDeCombustivelDTO bombaDeCombustivelDto) {
        bomba.setNomeBomba(bombaDeCombustivelDto.getNomeBomba());
        for (CombustivelDTO combustivelDto : bombaDeCombustivelDto.getCombustiveis()) {
            Combustivel combustivel = combustivelRepository.findByNomeCombustivel(combustivelDto.getNomeCombustivel());
            combustivel = combustivelRepository.getReferenceById(combustivel.getId());
            bomba.getCombustiveis().add(combustivel);
        }

    }
}
