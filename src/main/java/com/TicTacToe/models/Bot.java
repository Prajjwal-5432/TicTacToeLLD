package com.TicTacToe.models;

import com.TicTacToe.factories.botPlayingStrategyFactory.BotPlayingStrategyFactory;
import com.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(PlayerType.BOT, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = new BotPlayingStrategyFactory()
                .createBotPlayingStrategyFactoryForDifficulty(botDifficultyLevel);
    }

    public Move makeMove(Board board) {
        return this.botPlayingStrategy.makeNextMove(board, this);
    }
}
