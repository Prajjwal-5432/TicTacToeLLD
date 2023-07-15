package com.TicTacToe.strategies.botPlayingStrategies;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Move;
import com.TicTacToe.models.Player;

public interface BotPlayingStrategy {
    Move makeNextMove(Board board, Player player);
}
