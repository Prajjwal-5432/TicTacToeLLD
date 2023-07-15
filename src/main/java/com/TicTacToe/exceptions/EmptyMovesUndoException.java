package com.TicTacToe.exceptions;

public class EmptyMovesUndoException extends Exception {
    public EmptyMovesUndoException() {
        super("Undo operation was called when no moves existed");
    }
}
