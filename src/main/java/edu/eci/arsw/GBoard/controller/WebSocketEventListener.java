package edu.eci.arsw.GBoard.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;



@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
	IRoomRepository roomRepository;
	


    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("Received a new web socket connection");
      
    }

    //Aqui Ani
    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event){
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        System.out.println(headers.getSessionId());
        System.out.println(headers.getSessionAttributes()); //<-- esto es un diccionario
        System.out.println(headers.getDestination()); //<-- aqui esta el topic o sea al nombre del canal
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("End a new web socket connection");
    }
}