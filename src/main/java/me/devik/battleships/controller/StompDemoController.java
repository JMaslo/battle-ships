package me.devik.battleships.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devik.battleships.dto.HelloRequest;
import me.devik.battleships.dto.HelloResponse;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@Slf4j
@RequiredArgsConstructor
public class StompDemoController {

    private final SimpMessageSendingOperations messageTemplate;

    // this method is called when a client publishes a message to /app/topic/hello
    // the body (payload) of the message is deserialized into a HelloRequest object
    @MessageMapping("/topic/hello")
    @SendToUser("/queue/hello")
    private HelloResponse hello(@Payload HelloRequest message, StompHeaderAccessor headerAccessor) {
        log.info("User {} said hello", message.getName());
        headerAccessor.getSessionAttributes().put("username", message.getName());

        // this message is sent to all subscribers of /topic/greetings
        messageTemplate.convertAndSend("/topic/greetings", "User " + message.getName() + " said hello");

        // the response is serialized to a string and sent back to the sender,
        // who must be subscribed to /user/queue/hello
        return new HelloResponse("Hello, " + message.getName() + "!");
    }

    @EventListener
    public void handleWebSocketDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String name = (String) headerAccessor.getSessionAttributes().get("username");
        String message;
        if (name == null) {
            message = "Anonymous user disconnected without saying hello";
        } else {
            message = "User " + name + " disconnected";
        }
        messageTemplate.convertAndSend("/topic/greetings", message);
    }
}
