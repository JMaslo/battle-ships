package me.devik.battleships.service;

import me.devik.battleships.dto.GameInfo;
import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;
import me.devik.battleships.model.game.GameStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {

    private final ConcurrentHashMap<String, Game> storeOfGames = new ConcurrentHashMap<>();


    public Game createGames(String gameId, Player player) {
        Game game = new Game(gameId, player.getName() + "'s game");
        storeOfGames.put(game.getId(), game);
        return game;
    }

//    public void waitingForOpponent(UUID uuid) {
//        GameStatus gameStatus;
//        List<GameInfo> gameInfoList = new ArrayList<>();
//
//    }

    public Game joinGame(String gameId, Player player) {
        Game game = new Game(gameId, player.getName());
        storeOfGames.remove(game.getId(), game);
        return game;
    }




}