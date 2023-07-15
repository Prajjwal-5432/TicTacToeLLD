package com.TicTacToe.strategies.gameWinningStrategy;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Cell;
import com.TicTacToe.models.Player;

public interface GameWinningStrategy {
    boolean checkIfWon(Board board, Player player, Cell cell);
}
