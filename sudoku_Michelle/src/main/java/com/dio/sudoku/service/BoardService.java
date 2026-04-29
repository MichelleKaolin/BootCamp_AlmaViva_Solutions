package com.dio.sudoku.service;

import com.dio.sudoku.model.Board;
import com.dio.sudoku.model.Cell;
import com.dio.sudoku.exception.SudokuException;

public class BoardService {

    public Board initBoard(String data) {
        Board board = new Board();
        try {
            String[] entries = data.split(" ");
            for (String entry : entries) {
                // Formato esperado: row,col;val,fixed
                String[] parts = entry.split(";");
                String[] pos = parts[0].split(",");
                String[] valFixed = parts[1].split(",");

                int r = Integer.parseInt(pos[0]);
                int c = Integer.parseInt(pos[1]);
                int v = Integer.parseInt(valFixed[0]);
                boolean f = Boolean.parseBoolean(valFixed[1]);

                board.setCell(r, c, new Cell(v, f));
            }
        } catch (Exception e) {
            throw new SudokuException("Erro ao processar parser do tabuleiro: " + e.getMessage());
        }
        return board;
    }
}