package com.salesianostriana.pruebaproyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	//@Autowired
	//private HttpSession session;

	@GetMapping({"/", "/index"})
	public String showIndex(Model model) {
		//model.addAttribute("usuarioRegistrado", "Pantalla de Usuario");
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
	
	@GetMapping("/FilterUser/reservas")
	public String showReserva() {
		return "FilterUser/reservas";
	}
}
