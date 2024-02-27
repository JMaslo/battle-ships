package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.createGame.CreateGameRequest;
import me.devik.battleships.dto.selectGame.SelectGameResponse;
import me.devik.battleships.model.Player;
import me.devik.battleships.service.GameService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController {
    private GameService gameService;



    @MessageMapping("/topic/gameList")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse selectGame(StompHeaderAccessor stompHeaderAccessor) {

        return new SelectGameResponse("ok", "gameId", null);
    }

    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/selectGameResult")
    public SelectGameResponse createGame(@Payload CreateGameRequest message, StompHeaderAccessor stompHeaderAccessor) {
        Player player = (Player) stompHeaderAccessor.getSessionAttributes().get("player");

        if (player == null) {
            return new SelectGameResponse("error", "gameId", "Player is null!");
        }
        if (player.getGame() == null) {
            
        }

        return new SelectGameResponse("ok", "gameId", null);
    }
}
