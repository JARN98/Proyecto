package com.salesianostriana.pruebaproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/contact")
	public String showContact() {
		return "contact";
	}
	
	@GetMapping("/suites")
	public String showSuites() {
		return "contact";
	}
	
	@GetMapping("/Register")
	public String showRegister() {
		return "contact";
	}
	


}
