package com.dio.sudoku.service;

import com.dio.sudoku.model.Board;
import com.dio.sudoku.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorServiceTest {

    private ValidatorService validator;
    private Board board;

    @BeforeEach
    void setUp() {
        validator = new ValidatorService();
        board = new Board();
    }

    @Test
    void shouldIdentifyInvalidMoveInRow() {
        board.setCell(0, 0, new Cell(5, true));
        
        boolean isValid = validator.isValidMove(board, 0, 5, 5);
        
        assertFalse(isValid, "Não deveria permitir números repetidos na mesma linha");
    }

    @Test
    void shouldIdentifyInvalidMoveInColumn() {
        board.setCell(0, 0, new Cell(3, true));
        boolean isValid = validator.isValidMove(board, 5, 0, 3);
        
        assertFalse(isValid, "Não deveria permitir números repetidos na mesma coluna");
    }

    @Test
    void shouldIdentifyInvalidMoveInBox() {
        board.setCell(0, 0, new Cell(9, true));
        boolean isValid = validator.isValidMove(board, 1, 1, 9);
        
        assertFalse(isValid, "Não deveria permitir números repetidos no mesmo bloco 3x3");
    }

    @Test
    void shouldAllowValidMove() {
    
        boolean isValid = validator.isValidMove(board, 0, 0, 1);
        
        assertTrue(isValid, "Deveria permitir uma jogada válida em um tabuleiro vazio");
    }
}