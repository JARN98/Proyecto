package com.salesianostriana.pruebaproyecto.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.formbean.ReservaDeHabitacion;
import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
import com.salesianostriana.pruebaproyecto.services.ReservaService;

@Controller
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private HabitacionService habitacionService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/FilterUser/reservas")
	public String showReserva(Model model) {
		model.addAttribute("nuevaReserva", new ReservaDeHabitacion());
		return "FilterUser/reservas";
	}
	
	@PostMapping("/habitacionesReserva")
	public String showHab(Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {
		Iterable<Habitacion> habitacionesQueSePuedenReservar =  habitacionService.findAll();
		
		if(habitacionesQueSePuedenReservar != null ) {
			model.addAttribute("hPuedenReservarse", habitacionesQueSePuedenReservar);
			return "FilterUser/habitacionesReserva";
		}else {
			model.addAttribute("ErrorNohayHab", "Lo siento, en esas fechas no tenemos ninguna de las suites seleccionada disponibles");
			return "FilterUser/habitacionesReserva";
		}
		
	}
	
	
	public double calcularPrecio(Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion reserva, double precio) {
		
		
		LocalDateTime diaInicio = reserva.getFechaInicio();
		
		LocalDateTime diaFin = reserva.getFechaFin();
		
		Long dias = ChronoUnit.DAYS.between(diaInicio, diaFin);
	
		String temporadaAlta = "06-01";
		String temporadaBaja = "08-01";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		
		LocalDateTime fechaInicioTA = LocalDateTime.parse(temporadaAlta, formatter);
		LocalDateTime fechaInicioTB = LocalDateTime.parse(temporadaBaja, formatter);
		
		precio = precio * dias;
		
		if(diaInicio.isAfter(fechaInicioTA) && diaFin.isBefore(fechaInicioTB)) {
			precio = precio * 1.4;
		}
		
		return precio;	
			
		
	}
	
	@GetMapping("anadirReserva/{id}")
	public String showHabReservadas(@PathVariable("id") Long id, Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r, @ModelAttribute("usuarioActual") Usuario usuario) {
		Habitacion h = habitacionService.findOne(id);
		double precio = calcularPrecio(model, r, h.getPrecio());
		h.setPrecio(precio);
		Reserva reserva = new Reserva(r.getFechaInicio(), r.getFechaFin(), precio);
		reservaService.save(reserva);
		h.addReserva(reserva);
		usuario.addReserva(reserva);
		
		
		
		return "redirect:/FilterUser/reservas";
	}
	
}
