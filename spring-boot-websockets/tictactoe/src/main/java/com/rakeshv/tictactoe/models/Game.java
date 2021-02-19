package com.rakeshv.tictactoe.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String gameId;
    Player player1;
    Player player2;
    Object player1SessionId;
    Object player2SessionId;
    private GameStatus gameStatus;
    private int[][] board;
    private int[] row;
    private int[] column;
    private String winner;
    boolean isFirstPlayer;
    String currentTick;
}
