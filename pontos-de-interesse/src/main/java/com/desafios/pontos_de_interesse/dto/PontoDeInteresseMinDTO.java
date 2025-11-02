package com.desafios.pontos_de_interesse.dto;


import com.desafios.pontos_de_interesse.entities.PontoDeInteresse;

public class PontoDeInteresseMinDTO {

    String nome;
    Integer x;
    Integer y;

    public PontoDeInteresseMinDTO(){}

    public PontoDeInteresseMinDTO(String nome, Integer x, Integer y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public PontoDeInteresseMinDTO(PontoDeInteresse entity){
        nome = entity.getNome();
        x = entity.getX();
        y = entity.getY();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
