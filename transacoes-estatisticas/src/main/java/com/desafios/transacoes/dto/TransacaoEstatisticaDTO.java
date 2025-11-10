package com.desafios.transacoes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransacaoEstatisticaDTO {
    private Long contagem;
    private Double soma;
    private Double media;
    private Double min;
    private Double max;

    public TransacaoEstatisticaDTO() {}

    public TransacaoEstatisticaDTO(Long contagem, Double soma, Double media, Double min, Double max) {
        this.contagem = contagem;
        this.soma = soma;
        this.media = media;
        this.min = min;
        this.max = max;
    }

    public Long getContagem() {
        return contagem;
    }

    public void setContagem(Long contagem) {
        this.contagem = contagem;
    }

    public Double getSoma() {
        return soma;
    }

    public void setSoma(Double soma) {
        this.soma = soma;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
