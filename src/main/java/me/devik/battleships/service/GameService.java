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


    public GameService(Ships ship, Board gameBoard, Game game, Game game2, Game game3) {
        this.ship = ship;
        this.gameBoard = gameBoard;
        this.game = game;
        this.game2 = game2;
        this.game3 = game3;
    }

    public void createGame(Player player) {
        // Ask - Private/Public game
        // Private - can copy and ID of the game
    }




}
