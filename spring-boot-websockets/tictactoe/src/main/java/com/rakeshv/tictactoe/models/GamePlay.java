package com.rakeshv.tictactoe.models;

import lombok.Data;

@Data
public class GamePlay {
    private Tick type;
    private int x;
    private int y;
    private String gameId;
}
