package edu.eci.arsw.GBoard.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.model.Room;
import edu.eci.arsw.GBoard.model.RoomType;
import edu.eci.arsw.GBoard.model.Tag;
import edu.eci.arsw.GBoard.model.User;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;



@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
	IRoomRepository roomRepository;
    
    @Autowired
	IUserRepository userRepository;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("Received a new web socket connection");
      
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event){
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        //System.out.println(headers.getSessionId());
        //System.out.println(headers.getSessionAttributes().get("nick")); //<-- esto es un diccionario
        //System.out.println(headers.getDestination().replace("/topic/tablero.", "")); //<-- aqui esta el topic o sea al nombre del canal
        connectToRoom(headers.getSessionAttributes().get("nick").toString(), headers.getDestination().replace("/topic/tablero.", ""));
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("End a new web socket connection");
    }
    
    private void connectToRoom(String nickname, String r) {
    	
    	List<Room> rooms= roomRepository.findAll();
    	boolean f= false; int i=0;
    	for(Room room: rooms) {
    		if(room.getTitle().equals(r)) {
    			updateMembers(nickname,r,room);
    			f=true;break;
    		}i++;
    	}
    	if(!f) {createRoom(nickname,r,i);}
    }
    
    private void updateMembers(String nickname, String r, Room room) {
    	List<User> users= room.getMembers();
    	boolean f= false;
    	for(User u: users) {
    		if(u.getNickName().equals(nickname)) {
    			f= true; break;
    		}
    	}
    	if(!f) {
    		User user= userRepository.find(nickname);
        	users.add(user);
        	roomRepository.upadate(room);
    	}
    }
    
    private void createRoom(String nickname, String r,int i) {
    	User user= userRepository.find(nickname);
    	ArrayList<User> users= new ArrayList<User>();
    	users.add(user);
    	ArrayList<Tag> tags= new ArrayList<Tag>();
    	RoomType type= new RoomType("publica");
    	type.setId((long) 1);
    	Room room= new Room(r, user, users, tags, type, "");
    	room.setId((long) (i+1));
    	roomRepository.save(room);
    }
}