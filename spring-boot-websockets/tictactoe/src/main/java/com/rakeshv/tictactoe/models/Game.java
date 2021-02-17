package com.rakeshv.tictactoe.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String gameId;
    Player player1;
    Player player2;
    String player1SessionId;
    String player2SessionId;
    private GameStatus gameStatus;
    private int[][] board;
    private int[] row;
    private int[] column;
    private Tick winner;
}
