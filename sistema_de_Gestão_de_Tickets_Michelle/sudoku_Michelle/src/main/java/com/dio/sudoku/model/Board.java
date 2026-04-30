package com.dio.sudoku.model;

public class Board {
    public static final int SIZE = 9;
    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(0, false);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }
}