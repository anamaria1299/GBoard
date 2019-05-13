package edu.eci.arsw.GBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.model.Room;

@RestController
public class RoomController {

	@Qualifier("room")
	@Autowired
	IRoomService roomService;
	
	@RequestMapping(value="/rooms",method=RequestMethod.GET)
	public ResponseEntity<?> listAllRooms(){
		try {
	        return new ResponseEntity<>(roomService.getRooms(),HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/rooms",method=RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody Room room){
		try {
	    	roomService.save(room);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}
	
	@RequestMapping(value="/rooms/{title}", method=RequestMethod.GET)
	public ResponseEntity<?> getRoom(@PathVariable String title){
		try {
	        return new ResponseEntity<>(roomService.getRoomByTitle(title),HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/rooms/{title}", method= RequestMethod.PUT)
	public ResponseEntity<?> updateRoom(@RequestBody Room room){
		try {
			//System.out.println(room.getMembers().toString());
			roomService.updateRoom(room);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}

	@RequestMapping(value="/join",method=RequestMethod.POST)
	public ResponseEntity<?> joinRoom(HttpServletRequest req, HttpSession session){
		try {
			String roomName = req.getParameter("name");
			String nick = session.getAttribute("nick").toString();
	        return new ResponseEntity<>(roomService.joinRoom(roomName, nick),HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ResponseEntity<?> createRoom(HttpServletRequest req, HttpSession session){
		try {
			String nick = session.getAttribute("nick").toString();
			String roomName = req.getParameter("createName");
			return new ResponseEntity<>(roomService.createRoom(roomName, nick),HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}

	@RequestMapping(value="/searcher",method=RequestMethod.GET)
	public ResponseEntity<?> searchRoom(HttpServletRequest req, HttpSession session){
		try {
			return new ResponseEntity<>(roomService.searchProgress(req.getParameter("createName")),HttpStatus.ACCEPTED);
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
}
