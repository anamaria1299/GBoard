package edu.eci.arsw.GBoard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.model.Image;


@RestController
public class RoomController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Image sendMessage(@Payload Image roomImage) {
        return roomImage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Image addUser(@Payload Image chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("Image", chatMessage.getUser());
        return chatMessage;
    }
    
    @MessageMapping("/chat.sendMessage2")
    @SendTo("/topic/public2")
    public Image sendMessage2(@Payload Image roomImage) {
        return roomImage;
    }

    @MessageMapping("/chat.addUser2")
    @SendTo("/topic/public2")
    public Image addUser2(@Payload Image chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("Image", chatMessage.getUser());
        return chatMessage;
    }

}