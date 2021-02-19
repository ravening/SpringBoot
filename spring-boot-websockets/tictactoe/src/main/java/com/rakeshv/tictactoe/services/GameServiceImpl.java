package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.exceptions.GameNotFoundException;
import com.rakeshv.tictactoe.exceptions.InvalidGameException;
import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GamePlay;
import com.rakeshv.tictactoe.models.GameStatus;
import com.rakeshv.tictactoe.models.Player;
import com.rakeshv.tictactoe.models.Tick;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .gameId(String.valueOf(sessionId))
                .player1(player)
                .player1SessionId(sessionId)
                .isFirstPlayer(true)
                .currentTick("X")
                .gameStatus(GameStatus.NEW).build();
        GameStorage.getInstance().addGame(game);
        return game;
    }

    @Override
    public Optional<Game> connectToGame(Player player, String sessionId) {
        Optional<Game> gameOptional = Optional.ofNullable(GameStorage.getInstance().getGames().get(player.getGameId()));
        if (gameOptional.isEmpty()) {
            return Optional.empty();
        }

        Game game = gameOptional.get();
        if (game.getPlayer2() != null || game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            return Optional.empty();
        }

        return Optional.of(addPlayerToGame(game, player, sessionId));
    }

    @Override
    public Optional<Game> connectToRandomGame(Player player, Object sessionId) throws GameNotFoundException {
        Optional<Game> optionalGame = GameStorage.getInstance().getGames()
                .values().stream()
                .filter(g -> g.getGameStatus().equals(GameStatus.NEW))
                .findFirst();

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            log.info("Adding player {} to game id {}, created by {}", player.getLogin(), game.getGameId(), game.getPlayer1().getLogin());
            return Optional.of(addPlayerToGame(game, player, sessionId));
        }
        return Optional.empty();
    }

    private Game addPlayerToGame(Game game, Player player, Object sessionId) {
        game.setPlayer2SessionId(sessionId);
        game.setPlayer2(player);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().addGame(game);
        GameStorage.getInstance().addGame(sessionId.toString(), game);

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
        log.error("isfirst player ? " + game.isFirstPlayer());
        game.setFirstPlayer(!game.isFirstPlayer());
        log.error("isfirst player ? " + game.isFirstPlayer());
        game = gameEngine.checkWinner(game);

        game.setCurrentTick(game.isFirstPlayer() ? "X" : "O");
        GameStorage.getInstance().addGame(game);
        return game;
    }
}
