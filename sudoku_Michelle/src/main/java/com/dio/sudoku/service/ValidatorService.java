package com.dio.sudoku.service;

import com.dio.sudoku.model.Board;

public class ValidatorService {
    
    public boolean isValidMove(Board board, int row, int col, int value) {
        if (value < 1 || value > 9) return false;
        return !isInRow(board, row, value) && 
               !isInCol(board, col, value) && 
               !isInBox(board, row, col, value);
    }

    private boolean isInRow(Board board, int row, int value) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (board.getCell(row, i).getValue() == value) return true;
        }
        return false;
    }

    private boolean isInCol(Board board, int col, int value) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (board.getCell(i, col).getValue() == value) return true;
        }
        return false;
    }

    private boolean isInBox(Board board, int row, int col, int value) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board.getCell(i, j).getValue() == value) return true;
            }
        }
        return false;
    }
}