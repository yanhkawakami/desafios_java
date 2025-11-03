package com.desafios.transacoes.services;
import com.desafios.transacoes.dto.TransacaoDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransacaoService {

    private final Map<Long, TransacaoDTO> dados = new ConcurrentHashMap<>();

    private final AtomicLong contador = new AtomicLong();

    public void insert(TransacaoDTO transacaoDto){
        long id = contador.incrementAndGet();
        transacaoDto.setId(id);
        dados.put(id, transacaoDto);
    }


}
