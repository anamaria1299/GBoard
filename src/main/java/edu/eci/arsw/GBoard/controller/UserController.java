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
	public ResponseEntity<?> postLogIn(HttpServletRequest req, HttpSession session/* Model md ,*/){
	    try {
	    	String nick = req.getParameter("inputNick");
	    	String pass = req.getParameter("inputPass");
	    	userRepository.getCredentianls(nick, pass);
	        ResponseEntity<?> ans = new ResponseEntity<>("Accepted",HttpStatus.ACCEPTED);
	        session.setAttribute("nick", nick);
	        /*if(!ans.getBody().getName().isEmpty()) {
	        	session.setAttribute("inputNick", nick);
	        	return ans; //Aqui se retorna el usuario en json
	        }else {
	        	md.addAttribute("error_msg", "El usuario o la contraseña es incorrecto");
				System.out.println(ans.toString()+" Fallo  "+ans.getBody().getClass().getName()+" Null = "+ans.getBody().getName());
			}	    
	        return ans;//Aqui deberia volver a cargarse el index*/
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
	
	@RequestMapping(value="/users", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user){
		try {
	    	userRepository.save(user);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}
	
	
	@RequestMapping(value="/users/{nick}",method= RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String nick){
		try {
			//System.out.println(user.getProfile());
			//System.out.println(user.getGender());
	    	userRepository.upadate(user);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception ex) {
	        return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
	    }
	}

}
