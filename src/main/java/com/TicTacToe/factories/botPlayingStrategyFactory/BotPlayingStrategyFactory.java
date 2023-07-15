package com.TicTacToe.factories.botPlayingStrategyFactory;

import com.TicTacToe.models.BotDifficultyLevel;
import com.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;
import com.TicTacToe.strategies.botPlayingStrategies.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public BotPlayingStrategy createBotPlayingStrategyFactoryForDifficulty(BotDifficultyLevel botDifficultyLevel) {
        return switch (botDifficultyLevel) {
            case EASY, MEDIUM, HARD -> new RandomBotPlayingStrategy();
        };
    }
}
