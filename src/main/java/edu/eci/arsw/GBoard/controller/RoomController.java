package edu.eci.arsw.GBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.Persistence.Repositories.IRoomRepository;
import edu.eci.arsw.GBoard.model.Room;

@RestController
public class RoomController {
	
	@Autowired
	IRoomRepository roomRepository;
	
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
	public ResponseEntity<?> getUser(@PathVariable String title){
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

}
