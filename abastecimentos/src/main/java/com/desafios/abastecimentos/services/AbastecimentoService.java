package com.desafios.abastecimentos.services;

import com.desafios.abastecimentos.dto.AbastecimentoDTO;
import com.desafios.abastecimentos.dto.BombaDeCombustivelDTO;
import com.desafios.abastecimentos.entities.Abastecimento;
import com.desafios.abastecimentos.entities.BombaDeCombustivel;
import com.desafios.abastecimentos.repositories.AbastecimentoRepository;
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
}
