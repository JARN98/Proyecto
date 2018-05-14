package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;

@Controller
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;

	@PostMapping("/registerHabitacion")
	public String doHabitacion(@ModelAttribute("crearHabitacion") Habitacion habitacion, Model model) {

		habitacionService.save(habitacion);
		Iterable<Habitacion> lista = new HashSet<Habitacion>();
		lista = habitacionService.findAll();
		model.addAttribute("listaHabitaciones", lista);

		return "usuarios";
	}

}
