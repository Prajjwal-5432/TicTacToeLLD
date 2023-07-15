package com.TicTacToe;

import com.TicTacToe.controllers.GameController;
import com.TicTacToe.models.*;
import com.TicTacToe.strategies.gameWinningStrategy.GameWinningStrategy;
import com.TicTacToe.strategies.gameWinningStrategy.OrderOneGameWinningStrategy;

import java.util.List;

public class GameSimulator {
    public static void main(String[] args) {
        int dimension = 3;
        Player p1 = new HumanPlayer(new Symbol('X'));
        Player p2 = new Bot(new Symbol('O'), BotDifficultyLevel.EASY);
        GameWinningStrategy gameWinningStrategy = new OrderOneGameWinningStrategy();

        GameController gameController = new GameController();

        Game game = gameController.createGame(
                dimension,
                List.of(p1, p2),
                List.of(gameWinningStrategy)
        );

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("Please make the next move");
            gameController.makeMove(game);
            gameController.display(game);
        }

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)) {
            System.out.println("Game won");
            gameController.display(game);
        }

        if(gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
            System.out.println("No one won. Play Again");
            gameController.display(game);
        }

    }
}
