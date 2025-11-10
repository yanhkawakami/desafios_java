package com.desafios.transacoes.services;
import com.desafios.transacoes.dto.TransacaoDTO;
import com.desafios.transacoes.dto.TransacaoEstatisticaDTO;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();

    private final Map<Long, TransacaoDTO> transacoes = new ConcurrentHashMap<>();

    private final AtomicLong contador = new AtomicLong();

    public void insert(TransacaoDTO transacaoDto){
        long id = contador.incrementAndGet();
        transacaoDto.setId(id);
        transacoes.put(id, transacaoDto);
    }

    public void delete(){
        transacoes.clear();
    }

    public TransacaoEstatisticaDTO getEstatistica(){
        ZoneOffset zoneOffSet = ZoneOffset.of("-03:00");
        OffsetDateTime agora = OffsetDateTime.now(zoneOffSet);
        OffsetDateTime limite = agora.minusMinutes(60);
        Map<Long, TransacaoDTO> recentes = transacoes.entrySet().stream()
                .filter(entry -> entry.getValue().getDataHora().isBefore(agora))
                .filter(entry -> entry.getValue().getDataHora().isAfter(limite))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        DoubleSummaryStatistics estatisticas = recentes.values().stream()
                .mapToDouble(TransacaoDTO::getValor)
                .summaryStatistics();
        TransacaoEstatisticaDTO transacaoEstatisticaDto = new TransacaoEstatisticaDTO();

        // TODO: necessário formatar os campos Double para devolver dois campos decimais
        toDto(estatisticas, transacaoEstatisticaDto);
        return transacaoEstatisticaDto;
    }

    public void toDto(DoubleSummaryStatistics estatisticas, TransacaoEstatisticaDTO transacaoEstatisticaDto) {
        transacaoEstatisticaDto.setContagem(estatisticas.getCount());
        transacaoEstatisticaDto.setSoma(estatisticas.getSum());
        transacaoEstatisticaDto.setMedia(estatisticas.getAverage());
        transacaoEstatisticaDto.setMin(estatisticas.getMin());
        transacaoEstatisticaDto.setMax(estatisticas.getMax());
    }

}
