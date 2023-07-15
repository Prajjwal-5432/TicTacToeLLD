package com.TicTacToe.models;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private User user;
    private Scanner sc;
    public HumanPlayer(Symbol symbol) {
        super(PlayerType.HUMAN, symbol);
        this.sc = new Scanner(System.in);
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("Tell row number starting from 1");
        int row = sc.nextInt();
        System.out.println("Tell the column number from 1");
        int col = sc.nextInt();

        Move move = new Move();
        move.setCell(board.getCell(row - 1, col - 1));
        move.setPlayer(this);
        move.setSymbol(this.getSymbol());

        return move;
    }
}
