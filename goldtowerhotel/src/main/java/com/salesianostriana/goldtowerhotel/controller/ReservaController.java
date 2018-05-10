package com.salesianostriana.goldtowerhotel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservaController {
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("fil/reservas")
	public String welcome(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		return "fil/reservas";
	}
}
