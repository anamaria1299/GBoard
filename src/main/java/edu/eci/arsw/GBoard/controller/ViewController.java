package edu.eci.arsw.GBoard.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

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
	
	@RequestMapping("/profile")
	  String profile() {
	    return "profile";
	 }
}
