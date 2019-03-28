package edu.eci.arsw.GBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;

@RestController
public class UserController {
	
	@Autowired
	IUserRepository userRepository;
	
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers(){
	    try {
	    	
	        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/users/{nick}/{pass}",method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetLogIn(@PathVariable String nick, @PathVariable String pass){
	    try {
	        return new ResponseEntity<>(userRepository.getCredentianls(nick, pass),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}

}
