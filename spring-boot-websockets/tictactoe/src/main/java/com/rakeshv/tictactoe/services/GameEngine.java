package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.Player;
import com.rakeshv.tictactoe.models.Tick;
import org.springframework.stereotype.Component;

@Component
public class GameEngine {

    public Game checkWinner(Game game) {
        int[] row = game.getRow();
        int[] column = game.getColumn();
        Tick winner = null;

        for (int i = 0; i < 3; i++) {
            winner = row[i] == 3 ? Tick.X : row[i] == 6 ? Tick.O : null;
            if (winner == null) {
                winner = column[i] == 3 ? Tick.X : column[i] == 6 ? Tick.O : null;
            }
        }
        int[][] board = game.getBoard();
        int diagonal = board[0][0] + board[1][1] + board[2][2];
        if (winner == null) {
            winner = diagonal == 3 ? Tick.X : diagonal == 6 ? Tick.O : null;
        }
        int antiDiagonal = board[0][2] + board[1][1] + board[2][0];
        if (winner == null) {
            winner = antiDiagonal == 3 ? Tick.X : antiDiagonal == 6 ? Tick.O : null;
        }
        game.setWinner(winner);
        return game;
    }
}
