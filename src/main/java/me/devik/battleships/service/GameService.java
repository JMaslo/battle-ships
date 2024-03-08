package me.devik.battleships.service;

import lombok.RequiredArgsConstructor;
import me.devik.battleships.dto.GameInfo;
import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;
import me.devik.battleships.model.game.GameStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final ConcurrentHashMap<String, Game> storeOfGames = new ConcurrentHashMap<>();


    public Game createGames(String gameId, Player player) {
        Game game = new Game(gameId, player.getName() + "'s game", true);
        storeOfGames.put(game.getId(), game);
        return game;
    }

//    public void waitingForOpponent(UUID uuid) {
//        GameStatus gameStatus;
//        List<GameInfo> gameInfoList = new ArrayList<>();
//
//    }

    public Game joinGame(String gameId, Player player) {
        Game game = new Game(gameId, player.getName(), false);
        storeOfGames.remove(game.getId(), game);
        return game;
    }


    public final List<GameInfo> filterGames() {

        return storeOfGames
                .values()
                .stream()
                .filter(Game::isWaitingForOpponent)
                .map(game -> new GameInfo(game.getId(), game.getName()))
                .toList();
    }

}