package com.dio.sudoku;

import com.dio.sudoku.model.Board;
import com.dio.sudoku.service.*;
import com.dio.sudoku.util.BoardPrinter;
import com.dio.sudoku.exception.SudokuException;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        GameService gameService = new GameService();
        SudokuSolverService solverService = new SudokuSolverService();
        Scanner scanner = new Scanner(System.in);

        // Exemplo de String de inicialização (Algumas casas preenchidas)
        String initialState = "0,0;5,true 0,1;3,true 0,4;7,true 1,0;6,true 1,3;1,true 1,4;9,true 1,5;5,true " +
                             "2,1;9,true 2,2;8,true 2,7;6,true 3,0;8,true 3,4;6,true 3,8;3,true " +
                             "4,0;4,true 4,3;8,true 4,5;3,true 4,8;1,true 5,0;7,true 5,4;2,true 5,8;6,true " +
                             "6,1;6,true 6,6;2,true 6,7;8,true 7,3;4,true 7,4;1,true 7,5;9,true 7,8;5,true " +
                             "8,4;8,true 8,7;7,true 8,8;9,true";

        Board board = boardService.initBoard(initialState);

        System.out.println("=== BEM-VINDO AO SUDOKU JAVA CORE ===");
        
        while (true) {
            BoardPrinter.print(board);
            System.out.println("Opções: [1] Jogar [2] Resolver Automático [3] Sair");
            String op = scanner.nextLine();

            if (op.equals("3")) break;

            if (op.equals("2")) {
                if (solverService.solve(board)) {
                    System.out.println("Resolvido com sucesso!");
                } else {
                    System.out.println("Não foi possível resolver este tabuleiro.");
                }
                BoardPrinter.print(board);
                break;
            }

            if (op.equals("1")) {
                try {
                    System.out.print("Digite sua jogada (linha,coluna,valor) Ex: 0,2,4: ");
                    String input = scanner.nextLine();
                    String[] parts = input.split(",");
                    int r = Integer.parseInt(parts[0]);
                    int c = Integer.parseInt(parts[1]);
                    int v = Integer.parseInt(parts[2]);

                    gameService.makeMove(board, r, c, v);
                    
                    if (gameService.isComplete(board)) {
                        BoardPrinter.print(board);
                        System.out.println("PARABÉNS! Você venceu!");
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Erro: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }
}