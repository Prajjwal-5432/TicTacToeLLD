package com.TicTacToe.exceptions;

public class MultipleBotsExceptions extends Exception {
    public MultipleBotsExceptions() {
        super("A Game cannot have more than one bot players");
    }
}
