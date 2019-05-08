package edu.eci.arsw.GBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.GBoard.Persistence.UserException;
import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;
import edu.eci.arsw.GBoard.model.User;

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
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?> postLogIn(HttpServletRequest req, HttpSession session){
	    try {
	    	String nick = req.getParameter("inputNick");
	    	String pass = req.getParameter("inputPass");
	    	userRepository.getCredentianls(nick, pass);
	        ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
	        session.setAttribute("nick", nick);
	        return ans;
	    } catch (UserException ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	}
	
	@RequestMapping(value="/users/{nick}",method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String nick){
	    try {
	        return new ResponseEntity<>(userRepository.find(nick),HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    }
	} 
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(HttpServletRequest req,HttpSession session){
		try {
			User user = new User();
			user.setName(req.getParameter("inputName"));
			user.setLastName(req.getParameter("inputLast"));
			user.setNickName(req.getParameter("inputNick"));
			user.setPassword(req.getParameter("inputPass"));
	    	userRepository.save(user);
	    	ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.CREATED);
	    	session.setAttribute("nick", user.getNickName());
	        return ans;
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public ResponseEntity<?> postLogOut(HttpSession session){
	    ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
	    session.removeAttribute("nick");
		return ans;
	}
	
	@RequestMapping(value="/users/{nick}",method= RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String nick){
		try {
	    	userRepository.upadate(user);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}

}
