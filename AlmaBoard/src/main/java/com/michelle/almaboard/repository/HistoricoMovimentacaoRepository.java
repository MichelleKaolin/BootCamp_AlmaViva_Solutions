package com.michelle.almaboard.repository;

import com.michelle.almaboard.model.HistoricoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMovimentacaoRepository
        extends JpaRepository<HistoricoMovimentacao, Long> {
}