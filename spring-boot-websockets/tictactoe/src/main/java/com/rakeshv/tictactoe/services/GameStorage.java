package com.rakeshv.tictactoe.services;

import com.rakeshv.tictactoe.models.Game;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private static Map<String, Game> games;
    private static Map<Object, String> userMap;
    private static GameStorage instance;

    private GameStorage(){
        games = new ConcurrentHashMap<>();
        userMap = new ConcurrentHashMap<>();
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

    public Optional<Game> getGameById(String gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public void addGame(Game game) {
        games.put(game.getGameId(), game);
    }

    public void removeGame(String id) {
        games.remove(id);
    }
    public void addGame(String sessionId, Game game) {
        games.put(sessionId, game);
    }

    public void addPlayer(Object sessionId, String userName) {
        userMap.put(sessionId, userName);
    }

    public boolean checkIfUserExists(String userName) {
        return userMap.values().stream()
                .anyMatch(x -> x.equalsIgnoreCase(userName));
    }

    public String getUserName(Object sessionId) {
        return userMap.get(sessionId);
    }

    public void removePlayer(String sessionId) {
        userMap.remove(sessionId);
    }
}
