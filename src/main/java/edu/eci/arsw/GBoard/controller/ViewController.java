package edu.eci.arsw.GBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;

@Controller
public class ViewController implements ErrorController{
	
	@Autowired
	IUserRepository userRepository;

	@RequestMapping("/")
	  String index() {
	    return "index";
	 }
	
	@RequestMapping("/room/{title}")
	  String board() {
	    return "tablero";
	 }
	
	@RequestMapping("/boardB")
	  String boardB() {
	    return "tablero2";
	 }
	
	@RequestMapping("/u/{profile}")
	  String profile(@PathVariable String profile, Model model) {
		model.addAttribute("user", userRepository.find(profile));
	    return "profile";
	 }

	 @RequestMapping("/search")
	 String search() {
		 return "search";
	}

	@RequestMapping("/error")
	String error() {
		 return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
