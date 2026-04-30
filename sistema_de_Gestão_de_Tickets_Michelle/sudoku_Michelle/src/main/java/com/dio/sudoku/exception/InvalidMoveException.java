package com.dio.sudoku.exception;

public class InvalidMoveException extends SudokuException {
    public InvalidMoveException(String message) {
        super(message);
    }
}