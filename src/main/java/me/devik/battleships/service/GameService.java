package me.devik.battleships.service;

import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {

    private final ConcurrentHashMap<String, Game> storeOfGames = new ConcurrentHashMap<>();


    public Game createGames(Player player) {
        Game game = new Game(UUID.randomUUID().toString(), player.getName() + "'s game");
        storeOfGames.put(game.getId(), game);
        return game;
    }




}