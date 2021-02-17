package com.rakeshv.tictactoe.models;

import lombok.Getter;

@Getter
public enum Tick {
    X(1),
    O(2)
    ;

    private final int value;

    Tick(int val) {
        this.value = val;
    }
}
