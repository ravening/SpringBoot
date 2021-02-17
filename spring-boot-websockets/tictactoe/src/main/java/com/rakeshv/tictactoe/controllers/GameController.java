package com.rakeshv.tictactoe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.tictactoe.exceptions.GameNotFoundException;
import com.rakeshv.tictactoe.exceptions.InvalidGameException;
import com.rakeshv.tictactoe.models.Game;
import com.rakeshv.tictactoe.models.GamePlay;
import com.rakeshv.tictactoe.models.Player;
import com.rakeshv.tictactoe.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;

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
        log.info("Player {} started game with session id {}", player.getLogin(), sessionId);
        log.error("session id is {}", sessionId);
        Game game = gameService.createGame(player, sessionId);
        this.messagingTemplate.convertAndSend("/topic/start-game/" + player.getLogin(), game);
    }

    @MessageMapping("/play")
    public void playGame(@Payload GamePlay request) throws InvalidGameException, GameNotFoundException {
        log.info("playing the game {}", request);
        Game game = gameService.playGame(request);
        this.messagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
    }

    @MessageMapping("/connect/random")
    public void connectRandomGame(@Payload Player player, Message<?> message) throws GameNotFoundException {
        log.info("Connecting player {} to random game", player.getLogin());
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        Object sessionId = headers.get("simpSessionId");
        log.info("Player {} started game with session id {}", player.getLogin(), sessionId);
        log.error("session id is {}", sessionId);
        this.messagingTemplate.convertAndSend("/topic/connect-random/" + player.getLogin(),
                gameService.connectToRandomGame(player, sessionId));
    }

    @MessageMapping("/disconnect")
    public void disconnectPlayer(@Payload Player player) {
        log.error("Player {} disconnected", player);
    }
}
