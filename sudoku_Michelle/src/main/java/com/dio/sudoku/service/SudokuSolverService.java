package com.dio.sudoku.service;

import com.dio.sudoku.model.Board;

public class SudokuSolverService {
    private final ValidatorService validator = new ValidatorService();

    public boolean solve(Board board) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.getCell(row, col).isEmpty()) {
                    for (int num = 1; num <= 9; num++) {
                        if (validator.isValidMove(board, row, col, num)) {
                            board.getCell(row, col).setValue(num);
                            if (solve(board)) return true;
                            board.getCell(row, col).setValue(0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}