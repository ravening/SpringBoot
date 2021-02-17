package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.exceptions.GameNotFoundException;
import com.rakeshv.tictactoe.exceptions.InvalidGameException;
import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GamePlay;
import com.rakeshv.tictactoe.models.Player;

public interface GameService {
    Game createGame(Player player, Object sessionId);

    Game connectToGame(Player player2, String gameId) throws GameNotFoundException, InvalidGameException;

    Game connectToRandomGame(Player player, Object sessionId) throws GameNotFoundException;

    Game playGame(GamePlay gamePlay) throws GameNotFoundException, InvalidGameException;
}
