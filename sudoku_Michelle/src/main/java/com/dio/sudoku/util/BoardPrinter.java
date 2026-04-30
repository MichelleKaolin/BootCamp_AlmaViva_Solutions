package com.dio.sudoku.util;

import com.dio.sudoku.model.Board;

public class BoardPrinter {
    public static void print(Board board) {
        System.out.println("\n     0 1 2   3 4 5   6 7 8");
        System.out.println("   +-------+-------+-------+");
        for (int row = 0; row < Board.SIZE; row++) {
            if (row > 0 && row % 3 == 0) System.out.println("   |-------+-------+-------|");
            System.out.print(row + "  | ");
            for (int col = 0; col < Board.SIZE; col++) {
                int val = board.getCell(row, col).getValue();
                String display = (val == 0) ? "." : String.valueOf(val);
                System.out.print(display + " ");
                if ((col + 1) % 3 == 0) System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("   +-------+-------+-------+\n");
    }
}