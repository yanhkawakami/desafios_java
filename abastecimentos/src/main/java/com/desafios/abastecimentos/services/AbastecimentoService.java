package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.entities.Abastecimento;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.repositories.AbastecimentoRepository;
import com.desafios.abastecimentos.repositories.BombaDeCombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.EmptyContent;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AbastecimentoService {

    @Autowired
    AbastecimentoRepository repository;

    @Autowired
    BombaDeCombustivelRepository bombaDeCombustivelRepository;

    @Transactional(readOnly = true)
    public AbastecimentoDTO findById(Long id) {
        Optional<Abastecimento> result = repository.findById(id);
        Abastecimento abastecimento = result.orElseThrow(
                () -> new ResourceNotFoundException("Abastecimento com ID " + id + " não encontrado"));
        return new AbastecimentoDTO(abastecimento);
    }

    @Transactional(readOnly = true)
    public Page<AbastecimentoDTO> findAll(Pageable pageable) {
        Page<Abastecimento> result = repository.findAll(pageable);
        if (!result.getContent().isEmpty()) {
            return result.map(AbastecimentoDTO::new);
        }
        throw new EmptyContent("Não há abastecimentos cadastrados");
    }

    @Transactional
    public AbastecimentoDTO insert(@Valid AbastecimentoDTO abastecimentoDto) {
        try {
        Abastecimento abastecimento = new Abastecimento();
        copyDtoToEntity(abastecimento, abastecimentoDto);
        abastecimento = repository.save(abastecimento);
        return new AbastecimentoDTO(abastecimento);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("A bomba informada não existe");
        }
    }

    @Transactional
    public AbastecimentoDTO update(Long id, AbastecimentoDTO abastecimentoDto) {
        try {
            Abastecimento abastecimento = repository.getReferenceById(id);
            copyDtoToEntity(abastecimento, abastecimentoDto);
            abastecimento = repository.save(abastecimento);
            return new AbastecimentoDTO(abastecimento);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("A bomba informada não existe");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Abastecimento com ID " + id + " não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Abastecimento com ID " + id + " não encontrada");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade referencial: você está tentando deletar um abastecimento relacionado");
        }
    }

    public void copyDtoToEntity(Abastecimento abastecimento, AbastecimentoDTO abastecimentoDto) {
        abastecimento.setData(abastecimentoDto.getData());
        abastecimento.setValor(abastecimentoDto.getValor());
        abastecimento.setLitragem(abastecimentoDto.getLitragem());
        String nomeBomba = abastecimentoDto.getNomeBomba();
        BombaDeCombustivel bomba = bombaDeCombustivelRepository.findByNomeBomba(nomeBomba);
        if (bomba != null) {
            abastecimento.setBomba(bomba);
        } else {
            throw new ResourceNotFoundException("A bomba informada não existe");
        }

    }
}
