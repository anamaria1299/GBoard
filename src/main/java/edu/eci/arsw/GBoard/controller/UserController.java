package edu.eci.arsw.GBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.eci.arsw.GBoard.Persistence.GBoardException;
import edu.eci.arsw.GBoard.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.model.User;

@RestController
@Service
public class UserController {

	@Qualifier("user")
	@Autowired 
	IUserService userService;
	
	@RequestMapping(value="/users",method = RequestMethod.GET)
	public ResponseEntity<?> listAllUsers(){
	    try {
	        return new ResponseEntity<>(userService.getUsers(),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?> postLogIn(HttpServletRequest req, HttpSession session){
	    try {
	    	User us = userService.getCredentials(req);
	        ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
	        session.setAttribute("nick", us.getNickName());
	        return ans;
	    } catch (GBoardException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/users/{nick}",method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String nick){
	    try {
	        return new ResponseEntity<>(userService.getUserByNickname(nick),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	} 
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(HttpServletRequest req,HttpSession session){
		try {
	    	String nick= userService.createUser(req);
	    	ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.CREATED);
	    	session.setAttribute("nick", nick);
	        return ans;
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public ResponseEntity<?> postLogOut(HttpSession session){
		session.removeAttribute("nick");
		session.invalidate();
	    ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
		return ans;
	}
	
	@RequestMapping(value="/users/{nick}",method= RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String nick){
		try {
	    	userService.updateUser(user);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}

}
