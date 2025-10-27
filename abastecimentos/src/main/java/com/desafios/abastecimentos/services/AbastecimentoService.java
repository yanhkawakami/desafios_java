package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.dto.CombustivelDTO;
import com.desafios.abastecimentos.entities.Abastecimento;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.entities.Combustivel;
import com.desafios.abastecimentos.repositories.AbastecimentoRepository;
import com.desafios.abastecimentos.repositories.BombaDeCombustivelRepository;
import com.desafios.abastecimentos.services.exceptions.DatabaseException;
import com.desafios.abastecimentos.services.exceptions.EmptyContent;
import com.desafios.abastecimentos.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        if (result.getContent().size() != 0) {
            return result.map(x -> new AbastecimentoDTO(x));
        }
        throw new EmptyContent("Não há abastecimentos cadastrados");
    }

    @Transactional
    public AbastecimentoDTO insert(AbastecimentoDTO abastecimentoDto) {
        Abastecimento abastecimento = new Abastecimento();
        copyDtoToEntity(abastecimento, abastecimentoDto);
        String nomeBomba = abastecimentoDto.getNomeBomba();
        if (bombaDeCombustivelRepository.existsByNomeBomba(nomeBomba)) {
            abastecimento.setBomba(bombaDeCombustivelRepository.findByNomeBomba(nomeBomba));
            abastecimento = repository.save(abastecimento);
            return new AbastecimentoDTO(abastecimento);
        };
        throw new ResourceNotFoundException("A bomba informada não existe");

    }

    public void copyDtoToEntity(Abastecimento abastecimento, AbastecimentoDTO abastecimentoDto) {
        abastecimento.setData(abastecimentoDto.getData());
        BombaDeCombustivel bomba = new BombaDeCombustivel();
        bomba.setNomeBomba(abastecimentoDto.getNomeBomba());
        abastecimento.setBomba(bomba);
        abastecimento.setValor(abastecimentoDto.getValor());
        abastecimento.setLitragem(abastecimentoDto.getLitragem());
    }
}
