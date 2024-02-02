package me.devik.battleships.service;

import me.devik.battleships.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class PlayerService {
    private final Map<String, Player> players = new HashMap<>();

    public Player registerPlayer(String nickname) {

        if (players.containsKey(nickname)) {
            System.out.println("Player with nickname " + nickname + " already exists");
            return null;
        }

        Player newPlayer = players.get(nickname);
        players.put(nickname, newPlayer);
        return newPlayer;
    }
}
