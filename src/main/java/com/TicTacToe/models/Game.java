package com.TicTacToe.models;

import com.TicTacToe.exceptions.EmptyMovesUndoException;
import com.TicTacToe.exceptions.MultipleBotsExceptions;
import com.TicTacToe.strategies.gameWinningStrategy.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private List<GameWinningStrategy> gameWinningStrategies;
    private GameStatus gameStatus;
    private Player winner;
    private int lastMovedPlayerIndex;
    private Game() {
        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.gameWinningStrategies = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.lastMovedPlayerIndex = -1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<GameWinningStrategy> getGameWinningStrategies() {
        return gameWinningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public int getLastMovedPlayerIndex() {
        return lastMovedPlayerIndex;
    }

    public static Builder create() {
        return new Builder();
    }
    public void makeMove() {
        this.lastMovedPlayerIndex += 1;
        this.lastMovedPlayerIndex %= this.players.size();

        Move move = this.players.get(this.lastMovedPlayerIndex).makeMove(this.board);
        this.moves.add(move);

        move.getCell().setSymbol(move.getSymbol());

        for(GameWinningStrategy strategy: this.gameWinningStrategies) {
            if(strategy.checkIfWon(this.board, this.players.get(this.lastMovedPlayerIndex), move.getCell())) {
                gameStatus = GameStatus.ENDED;
                winner = this.players.get(this.lastMovedPlayerIndex);
                break;
            }
        }

        if(moves.size() == this.board.getDimension() * this.board.getDimension()) {
            gameStatus = GameStatus.DRAW;
            return;
        }
    }

    public boolean undo() throws EmptyMovesUndoException {
        if(this.moves.size() == 0) {
            throw new EmptyMovesUndoException();
        }

        Move lastMove = this.moves.get(this.moves.size() - 1);
        Cell relevantCell = lastMove.getCell();
        relevantCell.clearCell();
        this.lastMovedPlayerIndex = (this.lastMovedPlayerIndex - 1 + this.players.size()) % this.players.size();
        this.moves.remove(lastMove);
        return true;
    }
    public static class Builder {
        private List<Player> players;
        private Board board;
        private List<GameWinningStrategy> gameWinningStrategies;
        private int dimension;

        public Builder() {
            this.players = new ArrayList<>();
            this.gameWinningStrategies = new ArrayList<>();
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addPlayers(List<Player> players) {
            this.players.addAll(players);
            return this;
        }

        public Builder addGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
            this.gameWinningStrategies.add(gameWinningStrategy);
            return this;
        }

        public Builder addGameWinningStrategies(List<GameWinningStrategy> gameWinningStrategies) {
            this.gameWinningStrategies.addAll(gameWinningStrategies);
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private boolean checkIfSingleBotMax() {
            int count = 0;
            for(Player player: players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    count += 1;
                }
            }
            return count <= 1;
        }

        public Game build() throws MultipleBotsExceptions {
            if(!checkIfSingleBotMax()) {
                throw new MultipleBotsExceptions();
            }

            Game game = new Game();
            game.players.addAll(this.players);
            game.gameWinningStrategies.addAll(this.gameWinningStrategies);
            game.board = new Board(this.dimension);
            return game;
        }
    }
}
