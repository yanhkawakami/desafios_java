package com.desafios.pontos_de_interesse.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_ponto_de_interesse")
public class PontoDeInteresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    Integer x;
    Integer y;

    public PontoDeInteresse() {}

    public PontoDeInteresse(Long id, String nome, Integer x, Integer y) {
        this.id = id;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
