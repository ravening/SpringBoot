package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.exceptions.GameNotFoundException;
import com.rakeshv.tictactoe.exceptions.InvalidGameException;
import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GamePlay;
import com.rakeshv.tictactoe.models.GameStatus;
import com.rakeshv.tictactoe.models.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameEngine gameEngine;

    @Override
    public Game createGame(Player player, Object sessionId) {
        Game game = Game.builder()
                .board(new int[3][3])
                .row(new int[3])
                .column(new int[3])
                .gameId(UUID.randomUUID().toString())
                .player1(player)
                .gameStatus(GameStatus.NEW).build();
        GameStorage.getInstance().setGames(game);
        GameStorage.getInstance().addGameToSession(sessionId, game.getGameId());
        return game;
    }

    @Override
    public Game connectToGame(Player player, String gameId) throws GameNotFoundException, InvalidGameException {
        Optional<Game> gameOptional = Optional.ofNullable(GameStorage.getInstance().getGames().get(gameId));
        if (gameOptional.isEmpty()) {
            throw new GameNotFoundException("Unable to find game with id " + gameId);
        }

        Game game = gameOptional.get();
        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        return addPlayerToGame(game, player);
    }

    @Override
    public Game connectToRandomGame(Player player, Object sessionId) throws GameNotFoundException {
        Game game = GameStorage.getInstance().getGames()
                .values().stream()
                .filter(g -> g.getGameStatus().equals(GameStatus.NEW))
                .findFirst()
                .orElseThrow(() -> new GameNotFoundException("Unable to find any new games. Please create new game"));

        log.info("Adding player {} to game id {}, created by {}", player.getLogin(), game.getGameId(), game.getPlayer1().getLogin());
        GameStorage.getInstance().addGameToSession(sessionId, game.getGameId());
        return addPlayerToGame(game, player);
    }

    private Game addPlayerToGame(Game game, Player player) {
        game.setPlayer2(player);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGames(game);

        return game;
    }

    public Game playGame(GamePlay gamePlay) throws GameNotFoundException, InvalidGameException {
        Optional<Game> optionalGame = Optional.ofNullable(GameStorage.getInstance().getGames().get(gamePlay.getGameId()));
        if (optionalGame.isEmpty()) {
            throw new GameNotFoundException("Game not found with id " + gamePlay.getGameId());
        }

        Game game = optionalGame.get();
        if (game.getGameStatus().equals(GameStatus.FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        int[][] board = game.getBoard();
        board[gamePlay.getX()][gamePlay.getY()] = gamePlay.getType().getValue();
        int[] row = game.getRow();
        int[] column = game.getColumn();
        row[gamePlay.getX()] += gamePlay.getType().getValue();
        column[gamePlay.getY()] += gamePlay.getType().getValue();
        game.setBoard(board);
        game.setRow(row);
        game.setColumn(column);
        game = gameEngine.checkWinner(game);

        GameStorage.getInstance().setGames(game);
        return game;
    }
}
