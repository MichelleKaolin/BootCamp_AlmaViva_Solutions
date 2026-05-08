package com.michelle.almaboard.service;

import com.michelle.almaboard.dto.CardDTO;
import com.michelle.almaboard.exception.CardBloqueadoException;
import com.michelle.almaboard.exception.FluxoInvalidoException;
import com.michelle.almaboard.model.Card;
import com.michelle.almaboard.model.Coluna;
import com.michelle.almaboard.repository.CardRepository;
import com.michelle.almaboard.repository.ColunaRepository;
import org.springframework.stereotype.Service;
import com.michelle.almaboard.model.HistoricoMovimentacao;
import com.michelle.almaboard.repository.HistoricoMovimentacaoRepository;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;
    private final ColunaRepository colunaRepository;

    public CardService(
            CardRepository repository,
            ColunaRepository colunaRepository
    ) {
        this.repository = repository;
        this.colunaRepository = colunaRepository;
    }

    public Card criar(CardDTO dto) {

    Coluna coluna = colunaRepository.findById(dto.getColunaId())
            .orElseThrow();

    if (coluna.getTipo().name().equals("FINAL")
            || coluna.getTipo().name().equals("CANCELAMENTO")) {

        throw new FluxoInvalidoException(
                "Não é permitido criar cards nessa coluna"
        );
    }

    Card card = new Card();

    card.setTitulo(dto.getTitulo());
    card.setDescricao(dto.getDescricao());
    card.setColuna(coluna);

    return repository.save(card);
}

    public List<Card> listar() {
        return repository.findAll();
    }

  public Card mover(Long cardId, Long colunaDestinoId) {

    Card card = repository.findById(cardId)
            .orElseThrow();

    if (card.isBloqueado()) {
        throw new CardBloqueadoException(
                "O card está bloqueado"
        );
    }

    Coluna atual = card.getColuna();

    Coluna destino = colunaRepository.findById(colunaDestinoId)
            .orElseThrow();

    if (!atual.getBoard().getId().equals(destino.getBoard().getId())) {

        throw new FluxoInvalidoException(
                "Não é permitido mover cards entre boards diferentes"
        );
    }

    if (destino.getOrdem() != atual.getOrdem() + 1) {

        throw new FluxoInvalidoException(
                "Movimentação inválida"
        );
    }

    card.setColuna(destino);

    return repository.save(card);
}

    public Card bloquear(Long cardId, String motivo) {

        Card card = repository.findById(cardId)
                .orElseThrow();

        card.setBloqueado(true);
        card.setMotivoBloqueio(motivo);

        return repository.save(card);
    }

    public Card desbloquear(Long cardId) {

        Card card = repository.findById(cardId)
                .orElseThrow();

        card.setBloqueado(false);
        card.setMotivoBloqueio(null);

        return repository.save(card);
    }
}