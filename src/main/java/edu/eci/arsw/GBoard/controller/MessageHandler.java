package edu.eci.arsw.GBoard.controller;

import edu.eci.arsw.GBoard.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/message")
    public void handleMessageEvent(Message message){
        System.out.println(message);
        msgt.convertAndSend("/topic/message",message);
    }

}
