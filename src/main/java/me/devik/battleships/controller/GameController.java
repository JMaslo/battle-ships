package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.createGame.CreateGameRequest;
import me.devik.battleships.dto.selectGame.SelectGameResponse;
import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;
import me.devik.battleships.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController {
    private GameService gameService;
    private final GameController gameController;
    private SimpMessageSendingOperations messageTemplate;
    private UUID gameId = UUID.randomUUID();

    @MessageMapping("/topic/gameList")
//    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse selectGame(StompHeaderAccessor stompHeaderAccessor) {


        return new SelectGameResponse("ok", UUID.randomUUID().toString(), null);
    }

    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse createGame(@Payload CreateGameRequest message, StompHeaderAccessor stompHeaderAccessor) {
        Player player = (Player) stompHeaderAccessor.getSessionAttributes().get("player");

        if (player == null) {
            return new SelectGameResponse("error", UUID.randomUUID().toString(), "Player is null!");
        }
        if (player.getGame() == null) {
            this.gameService.createGames(gameId.toString(), player);

        }
        return new SelectGameResponse("ok", UUID.randomUUID().toString(), null);
    }

    @MessageMapping("/topic/joinGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse joinGame(StompHeaderAccessor stompHeaderAccessor) {
        Player player = (Player) stompHeaderAccessor.getSessionAttributes().get("player");

        if (player == null) {
            return new SelectGameResponse("error", UUID.randomUUID().toString(), "Player is null!");
        }
        if (player.getGame() == null) {
            this.gameService.joinGame(gameId.toString(), player);
        }
        return new SelectGameResponse("ok", UUID.randomUUID().toString(), "You have joined a game");
    }

//    private void broadcastGameInfos() {
//        messageTemplate.convertAndSend("/topic/gameList", gameService.getGameInfos());
//
//    }


}