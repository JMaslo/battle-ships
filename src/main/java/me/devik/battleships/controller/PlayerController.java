package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.setPlayerName.SetPlayerNameResponse;
import me.devik.battleships.dto.setPlayerName.SetPlayerNameRequest;
import me.devik.battleships.model.Player;
import me.devik.battleships.service.PlayerService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PlayerController {
    // What does this class do?
    // Communication with client
    private final PlayerService playerService;
    @MessageMapping("/topic/setName")
    @SendToUser("/queue/setNameResult")
    public SetPlayerNameResponse handleSetPlayerNameRequest(@Payload SetPlayerNameRequest message, StompHeaderAccessor stompHeaderAccessor) throws Exception{
        if (stompHeaderAccessor.getSessionAttributes().containsKey("player")) {
            return new SetPlayerNameResponse("error", "Player already has a nickname!");
        }
        Player player = playerService.registerPlayer(message.getName());
        if (player == null) {
            return new SetPlayerNameResponse("error", "Player with nickname " + HtmlUtils.htmlEscape(message.getName()) + " already exists!");
        }

        stompHeaderAccessor.getSessionAttributes().put("player", player);
        return new SetPlayerNameResponse("ok", null);
    }

    @EventListener
    public void handleWebSocketDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Player player = (Player) headerAccessor.getSessionAttributes().get("player");
        if (player != null) {
            playerService.unRegisterPlayer(player);
        }
    }

}
