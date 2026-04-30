package com.dio.sudoku.service;

import com.dio.sudoku.model.Board;
import com.dio.sudoku.exception.InvalidMoveException;

public class GameService {
    private final ValidatorService validator = new ValidatorService();

    public void makeMove(Board board, int row, int col, int value) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9) {
            throw new InvalidMoveException("Posição fora dos limites do tabuleiro.");
        }
        if (board.getCell(row, col).isFixed()) {
            throw new InvalidMoveException("Não é possível alterar uma célula fixa!");
        }
        if (!validator.isValidMove(board, row, col, value)) {
            throw new InvalidMoveException("Jogada inválida segundo as regras do Sudoku!");
        }
        board.getCell(row, col).setValue(value);
    }

    public boolean isComplete(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getCell(i, j).isEmpty()) return false;
            }
        }
        return true;
    }
}