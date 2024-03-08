package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.createGame.CreateGameRequest;
import me.devik.battleships.dto.selectGame.SelectGameResponse;
import me.devik.battleships.model.Player;
import me.devik.battleships.model.game.Game;
import me.devik.battleships.service.GameService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.util.HtmlUtils;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final SimpMessageSendingOperations messageTemplate;
    private final UUID gameId = UUID.randomUUID();

    @MessageMapping("/topic/gameList")
//    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse selectGame(StompHeaderAccessor stompHeaderAccessor) {


        return new SelectGameResponse("ok", gameId.toString(), null);
    }

    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse createGame(@Payload CreateGameRequest message, StompHeaderAccessor stompHeaderAccessor) {
        Player player = (Player) stompHeaderAccessor.getSessionAttributes().get("player");

        if (player == null) {
            return new SelectGameResponse("error", UUID.randomUUID().toString(), "Player is null!");
        }
        if (player.getGame() == null) {
            gameService.createGames(gameId.toString(), player);
            this.messageTemplate.convertAndSend("/topic/gameList", gameService.filterGames()); // Not done

        } else {
            return new SelectGameResponse("error", null, "You are already in a game!");
        }
        return new SelectGameResponse("ok", gameId.toString(), null);
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
        } else {
            return new SelectGameResponse("error", null, "You are already in the game!");
        }
        return new SelectGameResponse("ok", gameId.toString(), "You have joined a game");
    }

//    @EventListener
//    public void handleWebSocketDisconnectEvent(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String name = (String) headerAccessor.getSessionAttributes().get("username");
//        String message;
//        if (name == null) {
//            message = "Anonymous user disconnected!";
//        } else {
//            message = "User " + name + " disconnected!";
//        }
//        messageTemplate.convertAndSend("/topic/greetings", message);
//
//    }
}