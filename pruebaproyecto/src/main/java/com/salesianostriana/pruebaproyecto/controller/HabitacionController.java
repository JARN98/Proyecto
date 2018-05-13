package com.salesianostriana.pruebaproyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;

@Controller
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;

	
	@GetMapping("usuarios")
	public String showHabitacion(Model model) {
		model.addAttribute("crearHabitacion", new Habitacion());
		return "usuarios";
	}

	@PostMapping("/registerHabitacion")
	public String doHabitacion(@ModelAttribute("crearHabitacion") Habitacion habitacion, Model model) {
		
		habitacionService.save(habitacion);
		
		return "usuarios";

	}

}
