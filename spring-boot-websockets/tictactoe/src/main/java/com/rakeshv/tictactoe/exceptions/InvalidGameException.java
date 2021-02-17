package com.rakeshv.tictactoe.exceptions;

public class InvalidGameException extends Exception {
    private String message;

    public InvalidGameException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
