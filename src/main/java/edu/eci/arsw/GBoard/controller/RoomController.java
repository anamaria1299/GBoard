package edu.eci.arsw.GBoard.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.model.Room;
import edu.eci.arsw.GBoard.model.RoomType;
import edu.eci.arsw.GBoard.model.User;

@RestController
public class RoomController {
	
	@Autowired
	IRoomRepository roomRepository;

	@Autowired
	IUserRepository userRepository;
	
	@RequestMapping(value="/rooms",method=RequestMethod.GET)
	public ResponseEntity<?> listAllRooms(){
		try {
	        return new ResponseEntity<>(roomRepository.findAll(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/rooms",method=RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Room room){
		try {
	    	roomRepository.save(room);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}
	
	@RequestMapping(value="/rooms/{title}", method=RequestMethod.GET)
	public ResponseEntity<?> getRoom(@PathVariable String title){
		try {
	        return new ResponseEntity<>(roomRepository.find(title),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/rooms/{title}", method= RequestMethod.PUT)
	public ResponseEntity<?> updateRoom(@RequestBody Room room){
		try {
			//System.out.println(room.getMembers().toString());
			roomRepository.upadate(room);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}

	@RequestMapping(value="/join",method=RequestMethod.POST)
	public ResponseEntity<?> joinRoom(HttpServletRequest req, HttpSession session){
		try {
			String roomName = req.getParameter("name");
			roomRepository.addUser(userRepository.find(session.getAttribute("nick").toString()), roomName);
	        return new ResponseEntity<>(roomName,HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ResponseEntity<?> createRoom(HttpServletRequest req, HttpSession session){
		try {
			
			String roomName = req.getParameter("createName");
			User user= userRepository.find(session.getAttribute("nick").toString());
			ArrayList<User> users= new ArrayList<>();
			users.add(user);
			RoomType type= new RoomType("publica");
			Room room= new Room(roomName, user, users, null, type, "");
			roomRepository.save(room);
	        return new ResponseEntity<>(roomName,HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}

}
