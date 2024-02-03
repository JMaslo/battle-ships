package me.devik.battleships.service;

import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.controller.PlayerController;
import me.devik.battleships.model.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class PlayerService {
    // What does this class do?
    // Return something by which the PlayerController knows that an error has occurred

    private final Map<String, Player> players = new ConcurrentHashMap<>();

    public Player registerPlayer(String nickname) {
        Player newPlayer = new Player(nickname);
        Player previousPlayer = players.putIfAbsent(nickname, newPlayer);
        if (previousPlayer != null) {
            log.info("Somebody tried to register a nickname twice. Nickname: " + nickname);
            return null;
        }
        return newPlayer;
    }

    public void unRegisterPlayer(Player player) {
        players.remove(player.getName());
    }

}
