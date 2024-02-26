package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.createGame.createGameRequest;
import me.devik.battleships.dto.createGame.createGameResponse;
import me.devik.battleships.dto.joinGame.joinGameRequest;
import me.devik.battleships.dto.joinGame.joinGameResponse;
import me.devik.battleships.dto.selectGame.selectGameRequest;
import me.devik.battleships.dto.selectGame.selectGameResponse;
import me.devik.battleships.model.game.Game;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GameController {

    @MessageMapping("/topic/createGame")
    @SendToUser("/queue/createGameResult")
    public createGameResponse createGame(@Payload createGameRequest createGameRequest, StompHeaderAccessor stompHeaderAccessor) {
        
        return new createGameResponse();
    }

    @MessageMapping("/topic/joinGame")
    @SendToUser("/queue/joinGameResult")
    public joinGameResponse joinGame(@Payload joinGameRequest joinGameRequest, StompHeaderAccessor stompHeaderAccessor) {

        return new joinGameResponse("ok", null);
    }
    @MessageMapping("/topic/gameList")
    @SendToUser("/queue/selectGameResult")
    public selectGameResponse selectGame(@Payload selectGameRequest selectGameRequest, StompHeaderAccessor stompHeaderAccessor) {

        return new selectGameResponse("ok", "gameId", null);
    }
}
