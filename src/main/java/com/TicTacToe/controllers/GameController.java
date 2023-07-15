package com.TicTacToe.controllers;

import com.TicTacToe.exceptions.EmptyMovesUndoException;
import com.TicTacToe.models.Game;
import com.TicTacToe.models.GameStatus;
import com.TicTacToe.models.Player;
import com.TicTacToe.strategies.gameWinningStrategy.GameWinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension,
                           List<Player> players,
                           List<GameWinningStrategy> gameWinningStrategies) {
        Game game = null;
        try {
            game = Game
                    .create()
                    .setDimension(dimension)
                    .addPlayers(players)
                    .addGameWinningStrategies(gameWinningStrategies)
                    .build();
        } catch(Exception exception) {
            System.out.println("Something went wrong");
        }

        return game;
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public boolean undo(Game game) throws EmptyMovesUndoException {
        return game.undo();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void display(Game game) {
        game.getBoard().printBoard();
    }
}
