package edu.eci.arsw.GBoard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.GBoard.model.Image;


@Controller
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
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("Image", chatMessage.getUser());
        return chatMessage;
    }

}