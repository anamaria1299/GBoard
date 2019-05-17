package edu.eci.arsw.GBoard.controller;

import edu.eci.arsw.GBoard.Persistence.GBoardException;

import javax.servlet.http.HttpServletResponse;
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
	String board(HttpSession session,  HttpServletResponse res) {
		CleanCache(res);
		if(	session.getAttribute("nick") != null)
				return "tablero";
		return "error";
		
	}

	@RequestMapping("/u/{profile}")
	  String profile(@PathVariable String profile, Model model, HttpSession session, HttpServletResponse res) {
			try {
				model.addAttribute("user", userRepository.find(profile));
				CleanCache(res);
			} catch (GBoardException e) {
				e.printStackTrace();
			}
			return "profile";
			}

	@RequestMapping("/search")
	String search(HttpServletResponse res) {
		CleanCache(res);
		return "search";
	}

	@RequestMapping("/chat")
	String chat() {return "chat";}

	@RequestMapping("/error")
	String error() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	private void CleanCache(HttpServletResponse res){
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    res.setDateHeader("Expires", 0);
	}
}
