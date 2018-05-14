package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
import com.salesianostriana.pruebaproyecto.services.ReservaService;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@Controller
public class IndexController {
	
	//@Autowired
	//private HttpSession session;
	
	@Autowired
	private HabitacionService habitacionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ReservaService reservaService;

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
	
	@GetMapping("/usuarios")
	public String showHabitacion(Model model) {
		model.addAttribute("crearHabitacion", new Habitacion());
		Iterable<Habitacion> lista = new HashSet<Habitacion>();
		lista = habitacionService.findAll();
		model.addAttribute("listaHabitaciones", lista);
		
		Iterable<Usuario> listaU = new HashSet<Usuario>();
		listaU = usuarioService.findAll();
		model.addAttribute("listaUsuarios", listaU);
		return "usuarios";
	}
	
	@GetMapping("/datos")
	public String showDatos(Model model) {
		Iterable<Reserva> listaR = new HashSet<Reserva>();
		listaR = reservaService.findAll();
		model.addAttribute("listaReservas", listaR);
		return "datos";
	}
}
