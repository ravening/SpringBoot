package com.rakeshv.tictactoe.controllers;

import com.rakeshv.tictactoe.exceptions.GameNotFoundException;
import com.rakeshv.tictactoe.exceptions.InvalidGameException;
import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GamePlay;
import com.rakeshv.tictactoe.models.Player;
import com.rakeshv.tictactoe.models.Response;
import com.rakeshv.tictactoe.services.GameService;
import com.rakeshv.tictactoe.services.GameStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@Slf4j
public class GameController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private GameService gameService;

    @MessageMapping("/start")
    public void startGame(@Payload Player player, Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        Object sessionId = headers.get("simpSessionId");
        if (checkIfUserExists(player.getLogin())) {
            sendNotification("/topic/invalid-username/" + player.getLogin(),
                    Response.builder().message("Username already exists. Select different username")
                            .sessionid(sessionId).build());
        } else {
            log.info("Player {} started game with session id {}", player.getLogin(), sessionId);
            GameStorage.getInstance().addPlayer(sessionId, player.getLogin());
            Game game = gameService.createGame(player, sessionId);
            this.messagingTemplate.convertAndSend("/topic/start-game/" + player.getLogin(), game);
        }
    }

    @MessageMapping("/play")
    public void playGame(@Payload GamePlay request) throws InvalidGameException, GameNotFoundException {
        log.info("playing the game {}", request);
        Game game = gameService.playGame(request);
        if (game.getWinner() != null) {
            String player1 = game.getPlayer1().getLogin();
            String player2 = game.getPlayer2().getLogin();
            this.messagingTemplate.convertAndSend("/topic/game-over/" + player1,
                    Response.builder().message("Winner is " + game.getWinner()).game(game).build());
            this.messagingTemplate.convertAndSend("/topic/game-over/" + player2,
                    Response.builder().message("Winner is " + game.getWinner()).game(game).build());
            game.setWinner(null);
            GameStorage.getInstance().addGame(game);
        } else {
            this.messagingTemplate.convertAndSend("/topic/game-progress/" + game.getPlayer1().getLogin(), game);
            this.messagingTemplate.convertAndSend("/topic/game-progress/" + game.getPlayer2().getLogin(), game);
        }
    }

    @MessageMapping("/connect/random")
    public void connectRandomGame(@Payload Player player, Message<?> message) throws GameNotFoundException {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        Object sessionId = headers.get("simpSessionId");
        if (checkIfUserExists(player.getLogin())) {
            sendNotification("/topic/invalid-username/" + player.getLogin(),
                    Response.builder().message("Username already exists. Select different username").build());
        } else {
            log.info("connecting Player {} to random game", player.getLogin());
            Optional<Game> optionalGame = gameService.connectToRandomGame(player, sessionId);
            if (optionalGame.isPresent()) {
                sendGameNotification(player, sessionId.toString(), optionalGame.get());
            } else {
                sendNotification("/topic/game-not-found/" + player.getLogin(),
                        Response.builder().message("No game found. Please create new game").build());
            }
        }
    }

    @MessageMapping("/connect/gameid")
    public void connectToGameId(@Payload Player player, Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        Object sessionId = headers.get("simpSessionId");
        if (checkIfUserExists(player.getLogin())) {
            sendNotification("/topic/invalid-username/" + player.getLogin(),
                    Response.builder().message("Username already exists. Select different username").build());
        } else {
            log.info("User {} is trying to connect to game id {}", player.getLogin(), player.getGameId());
            Optional<Game> optionalGame = gameService.connectToGame(player, sessionId.toString());
            if (optionalGame.isPresent()) {
                log.info("Found the game with id {}", player.getGameId());
                sendGameNotification(player, sessionId.toString(), optionalGame.get());
            } else {
                sendNotification("/topic/game-not-found/" + player.getLogin(),
                        Response.builder().message("No game found. Please create new game").build());
            }
        }
    }

    @MessageMapping("/reset")
    public void resetGame(@Payload Player player, Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        Object sessionId = headers.get("simpSessionId");
        Game game = gameService.resetGame(sessionId.toString(), player);
        Response response = Response.builder().message("Game has been reset by " + player.getLogin()).build();
        this.messagingTemplate.convertAndSend("/topic/terminate-game/" + game.getPlayer1().getLogin(), response);
        this.messagingTemplate.convertAndSend("/topic/terminate-game/" + game.getPlayer2().getLogin(), response);
    }

    @MessageMapping("/draw-game")
    public void drawGame(@Payload Player player, Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        Object sessionId = headers.get("simpSessionId");
        gameService.resetGame(sessionId.toString(), player);
    }
    @MessageMapping("/disconnect")
    public void disconnectPlayer(@Payload Player player) {
        log.error("Player {} disconnected", player);
        GameStorage.getInstance().removeGame(player.getLogin());
        GameStorage.getInstance().removePlayer(player.getLogin());
    }

    private boolean checkIfUserExists(String username) {
        return GameStorage.getInstance().checkIfUserExists(username);
    }

    private void sendNotification(String prefix, Object response) {
        this.messagingTemplate.convertAndSend(prefix, response);
    }

    private void sendGameNotification(Player player, String sessionId, Game game) {
        GameStorage.getInstance().addPlayer(sessionId, player.getLogin());
        sendNotification("/topic/connect-random/player2/" + player.getLogin(), game);
        sendNotification("/topic/connect-random/player1/" + game.getPlayer1().getLogin(), game);
    }
}
