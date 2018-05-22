package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
import com.salesianostriana.pruebaproyecto.services.ReservaService;

@Controller
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;

	@Autowired
	private ReservaService reservaService;



	@PostMapping("/registerHabitacion")
	public String doHabitacion(@ModelAttribute("crearHabitacion") Habitacion habitacion, Model model,
			@ModelAttribute("listaHabitaciones") HashSet<Habitacion> listaHabitaciones) {

		habitacionService.save(habitacion);
		listaHabitaciones.add(habitacion);
		// Iterable<Habitacion> lista = new HashSet<Habitacion>();
		// lista = habitacionService.findAll();
		// model.addAttribute("listaHabitaciones", lista);

		return "redirect:/usuarios#listaHabitaciones";
	}

	@GetMapping("usuarios/#listaHabitacion")
	public String showListH() {
		return "usuarios#listaHabitacion";
	}

}
