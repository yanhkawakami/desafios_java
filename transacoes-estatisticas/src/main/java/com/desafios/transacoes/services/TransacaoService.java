package com.desafios.transacoes.services;
import com.desafios.transacoes.dto.TransacaoDTO;
import com.desafios.transacoes.dto.TransacaoEstatisticaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

    private final Map<Long, TransacaoDTO> transacoes = new ConcurrentHashMap<>();

    private final AtomicLong contador = new AtomicLong();

    public void insert(TransacaoDTO transacaoDto){
        log.info("A transação com dataHora " + transacaoDto.getDataHora() + " e valor R$" + transacaoDto.getValor() + " está sendo salva");
        long id = contador.incrementAndGet();
        transacaoDto.setId(id);
        transacoes.put(id, transacaoDto);
        log.info("A transação foi salva com ID " + id);
    }

    public void delete(){
        transacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public TransacaoEstatisticaDTO getEstatistica(String janelaSegundos){
        ZoneOffset zoneOffSet = ZoneOffset.of("-03:00");
        OffsetDateTime agora = OffsetDateTime.now(zoneOffSet);
        OffsetDateTime limite = agora.minusMinutes(Long.parseLong(janelaSegundos));
        log.info("Transações a partir de " + limite + " serão utilizadas para gerar as estatísticas" );
        Map<Long, TransacaoDTO> recentes = transacoes.entrySet().stream()
                .filter(entry -> entry.getValue().getDataHora().isBefore(agora))
                .filter(entry -> entry.getValue().getDataHora().isAfter(limite))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        DoubleSummaryStatistics estatisticas = recentes.values().stream()
                .mapToDouble(TransacaoDTO::getValor)
                .summaryStatistics();
        TransacaoEstatisticaDTO transacaoEstatisticaDto = new TransacaoEstatisticaDTO();

        toDto(estatisticas, transacaoEstatisticaDto);
        log.info("Estatísticas calculadas com sucesso!");
        return transacaoEstatisticaDto;
    }

    public void toDto(DoubleSummaryStatistics estatisticas, TransacaoEstatisticaDTO transacaoEstatisticaDto) {
        transacaoEstatisticaDto.setContagem(estatisticas.getCount());
        transacaoEstatisticaDto.setSoma(arredondarParaDuasCasas(estatisticas.getSum()));

        // Tratar casos especiais para média (pode ser NaN quando não há dados)
        double media = estatisticas.getAverage();
        transacaoEstatisticaDto.setMedia(Double.isNaN(media) ? 0.0 : arredondarParaDuasCasas(media));

        // Tratar casos especiais para min/max (podem ser Infinity quando não há dados)
        double min = estatisticas.getMin();
        transacaoEstatisticaDto.setMin(Double.isInfinite(min) ? 0.0 : arredondarParaDuasCasas(min));

        double max = estatisticas.getMax();
        transacaoEstatisticaDto.setMax(Double.isInfinite(max) ? 0.0 : arredondarParaDuasCasas(max));
    }

    private Double arredondarParaDuasCasas(double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            return 0.0;
        }
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
