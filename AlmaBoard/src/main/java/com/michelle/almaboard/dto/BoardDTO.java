package com.michelle.almaboard.dto;

import java.util.List;

public class BoardDTO {

    private String nome;

    private List<ColunaDTO> colunas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ColunaDTO> getColunas() {
        return colunas;
    }

    public void setColunas(List<ColunaDTO> colunas) {
        this.colunas = colunas;
    }
}