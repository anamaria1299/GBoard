package edu.eci.arsw.GBoard.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;

@Controller
public class ViewController {
	
	@Autowired
	IUserRepository userRepository;

	@RequestMapping("/")
	  String index() {
	    return "index";
	 }
	
	@RequestMapping("/board")
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
}
