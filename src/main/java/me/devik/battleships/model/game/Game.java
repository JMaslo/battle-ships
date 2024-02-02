package me.devik.battleships.model.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Game {

    String id;
    String name_of_game;


    public void listOfGames() {

        Game game = new Game();
        Game game2 = new Game();
        Game game3 = new Game();
        Map<String, Game> listOfGames = new ConcurrentHashMap<>();
        listOfGames.put("12345", game);
        listOfGames.put("67890", game2);
        listOfGames.put("87654", game3);

    }

}
