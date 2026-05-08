package com.michelle.almaboard.dto;

import com.michelle.almaboard.model.TipoColuna;

public class ColunaDTO {

    private String nome;

    private int ordem;

    private TipoColuna tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public TipoColuna getTipo() {
        return tipo;
    }

    public void setTipo(TipoColuna tipo) {
        this.tipo = tipo;
    }
}