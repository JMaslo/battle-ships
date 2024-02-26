package me.devik.battleships.service;

import me.devik.battleships.model.Board;
import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;
import me.devik.battleships.model.ships.Ships;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {

    private Ships ship;
    private Board gameBoard;

    Map<String, Game> listOfGames = new ConcurrentHashMap<>();
    Game game;
    Game game2;
    Game game3;







}
