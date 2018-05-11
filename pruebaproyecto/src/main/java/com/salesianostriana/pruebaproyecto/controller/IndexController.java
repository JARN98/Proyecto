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
		return "suites";
	}

	@GetMapping("/Register")
	public String showRegister() {
		return "Register";
	}

	@GetMapping("/diamante")
	public String showSuite1() {
		return "diamante";
	}

	@GetMapping("/esmeralda")
	public String showSuite2() {
		return "esmeralda";
	}

	@GetMapping("/oro")
	public String showSuite3() {
		return "oro";
	}
	
	@GetMapping("/hotel")
	public String showHotel() {
		return "hotel";
	}
}
