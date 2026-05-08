package com.michelle.almaboard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoricoMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String colunaOrigem;

    private String colunaDestino;

    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Long getId() {
        return id;
    }

    public String getColunaOrigem() {
        return colunaOrigem;
    }

    public void setColunaOrigem(String colunaOrigem) {
        this.colunaOrigem = colunaOrigem;
    }

    public String getColunaDestino() {
        return colunaDestino;
    }

    public void setColunaDestino(String colunaDestino) {
        this.colunaDestino = colunaDestino;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}