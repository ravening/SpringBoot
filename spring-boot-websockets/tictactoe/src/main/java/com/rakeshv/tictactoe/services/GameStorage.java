package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.models.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private static Map<String, Game> games;
    private static Map<Object, String> sessionMap;
    private static GameStorage instance;

    private GameStorage(){
        games = new ConcurrentHashMap<>();
        sessionMap = new ConcurrentHashMap<>();
    }

    public static synchronized GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }

        return instance;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public Game getGameById(String gameId) {
        return games.get(gameId);
    }

    public void setGames(Game game) {
        games.put(game.getGameId(), game);
    }

    public void addGameToSession(Object sessionId, String gameId) {
        sessionMap.put(sessionId, gameId);
    }

    public String getGameBySessionId(Object sessionId) {
        return sessionMap.get(sessionId);
    }
}
