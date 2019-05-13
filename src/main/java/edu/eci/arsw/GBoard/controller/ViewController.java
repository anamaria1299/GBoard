package edu.eci.arsw.GBoard.controller;

import edu.eci.arsw.GBoard.Persistence.GBoardException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.eci.arsw.GBoard.Persistence.Repositories.IUserRepository;

@Controller
public class ViewController implements ErrorController {

	@Autowired
	IUserRepository userRepository;

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping("/room/{title}")
	String board(HttpSession session) {
		if(	session.getAttribute("nick") != null)
				return "tablero";
		return "error";
		
	}



	@RequestMapping("/u/{profile}")
	  String profile(@PathVariable String profile, Model model, HttpSession session) {
			try {
				model.addAttribute("user", userRepository.find(profile));
			} catch (GBoardException e) {
				e.printStackTrace();
			}
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
