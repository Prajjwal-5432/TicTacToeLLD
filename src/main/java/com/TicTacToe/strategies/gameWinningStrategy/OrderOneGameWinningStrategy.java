package com.TicTacToe.strategies.gameWinningStrategy;

import com.TicTacToe.models.Board;
import com.TicTacToe.models.Cell;
import com.TicTacToe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowCharCounts;
    private List<HashMap<Character, Integer>> colCharCounts;

    private void initialize(Board board) {
        rowCharCounts = new ArrayList<>();
        colCharCounts = new ArrayList<>();
        for(int i = 0; i < board.getDimension(); i++) {
            rowCharCounts.add(new HashMap<>());
            colCharCounts.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkIfWon(Board board, Player player, Cell cell) {
        if(rowCharCounts == null) {
            initialize(board);
        }
        int row = cell.getRow();
        int col = cell.getCol();

        if(!rowCharCounts.get(row).containsKey(cell.getSymbol().getCharacter())) {
            rowCharCounts.get(row).put(cell.getSymbol().getCharacter(), 0);
        }
        if(!colCharCounts.get(col).containsKey(cell.getSymbol().getCharacter())) {
            colCharCounts.get(col).put(cell.getSymbol().getCharacter(), 0);
        }

        rowCharCounts.get(row).put(
                cell.getSymbol().getCharacter(),
                rowCharCounts.get(row).get(cell.getSymbol().getCharacter()) + 1
        );

        colCharCounts.get(col).put(
                cell.getSymbol().getCharacter(),
                colCharCounts.get(col).get(cell.getSymbol().getCharacter()) + 1
        );

        if(rowCharCounts.get(row).get(cell.getSymbol().getCharacter()).equals(board.getDimension())) {
            return true;
        }
        if(colCharCounts.get(col).get(cell.getSymbol().getCharacter()).equals(board.getDimension())) {
            return true;
        }

        return false;
    }
}
