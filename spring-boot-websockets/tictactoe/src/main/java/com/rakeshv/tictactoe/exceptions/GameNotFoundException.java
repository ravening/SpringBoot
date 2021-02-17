package com.rakeshv.tictactoe.exceptions;

public class GameNotFoundException extends Exception {
    private String message;

    public GameNotFoundException(String message) {
        this.message = message;
    }
}
