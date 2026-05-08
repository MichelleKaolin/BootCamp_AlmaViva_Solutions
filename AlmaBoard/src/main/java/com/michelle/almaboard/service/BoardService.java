package com.michelle.almaboard.service;

import com.michelle.almaboard.dto.BoardDTO;
import com.michelle.almaboard.dto.ColunaDTO;
import com.michelle.almaboard.exception.BoardInvalidoException;
import com.michelle.almaboard.model.Board;
import com.michelle.almaboard.model.Coluna;
import com.michelle.almaboard.model.TipoColuna;
import com.michelle.almaboard.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository repository;

    public BoardService(BoardRepository repository) {
        this.repository = repository;
    }

    public Board criar(BoardDTO dto) {

        Board board = new Board();

        board.setNome(dto.getNome());

        List<Coluna> colunas = new ArrayList<>();

        for (ColunaDTO colunaDTO : dto.getColunas()) {

            Coluna coluna = new Coluna();

            coluna.setNome(colunaDTO.getNome());
            coluna.setOrdem(colunaDTO.getOrdem());
            coluna.setTipo(colunaDTO.getTipo());
            coluna.setBoard(board);

            colunas.add(coluna);
        }

        board.setColunas(colunas);

        validarBoard(board);

        return repository.save(board);
    }

    public List<Board> listar() {
        return repository.findAll();
    }

    private void validarBoard(Board board) {

        if (board.getColunas().size() < 3) {
            throw new BoardInvalidoException(
                    "O board deve possuir pelo menos 3 colunas"
            );
        }

        long inicial = board.getColunas()
                .stream()
                .filter(c -> c.getTipo() == TipoColuna.INICIAL)
                .count();

        long finalColuna = board.getColunas()
                .stream()
                .filter(c -> c.getTipo() == TipoColuna.FINAL)
                .count();

        long cancelamento = board.getColunas()
                .stream()
                .filter(c -> c.getTipo() == TipoColuna.CANCELAMENTO)
                .count();

        if (inicial != 1) {
            throw new BoardInvalidoException(
                    "Deve existir exatamente uma coluna INICIAL"
            );
        }

        if (finalColuna != 1) {
            throw new BoardInvalidoException(
                    "Deve existir exatamente uma coluna FINAL"
            );
        }

        if (cancelamento != 1) {
            throw new BoardInvalidoException(
                    "Deve existir exatamente uma coluna CANCELAMENTO"
            );
        }
    }
}