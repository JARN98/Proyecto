package com.salesianostriana.pruebaproyecto.controller;

import java.time.LocalDate;
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

	public ReservaDeHabitacion reservaDeHabitacion;

	@GetMapping("/FilterUser/reservas")
	public String showReserva(Model model) {
		model.addAttribute("nuevaReserva", new ReservaDeHabitacion());
		return "FilterUser/reservas";
	}

	@PostMapping("/habitacionesReserva")
	public String showHab(Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {
		Iterable<Habitacion> habitacionesQueSePuedenReservar = habitacionService.findAll();

		reservaDeHabitacion = new ReservaDeHabitacion(r.getFechaInicio(), r.getFechaFin(), r.getTipoHab());

		if (habitacionesQueSePuedenReservar != null) {
			model.addAttribute("hPuedenReservarse", habitacionesQueSePuedenReservar);
			return "FilterUser/habitacionesReserva";
		} else {
			model.addAttribute("ErrorNohayHab",
					"Lo siento, en esas fechas no tenemos ninguna de las suites seleccionada disponibles");
			return "FilterUser/habitacionesReserva";
		}

	}

	public double calcularPrecio(Model model, double precio) {

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate diaInicio = LocalDate.parse(reservaDeHabitacion.getFechaInicio(), formateoFecha);

		LocalDate diaFin = LocalDate.parse(reservaDeHabitacion.getFechaFin(), formateoFecha);

		Long dias = ChronoUnit.DAYS.between(diaInicio, diaFin);

		precio = precio * dias;
		
		int mesInicioTA = 6;
		int mesFinTA = 7;
		if (diaInicio.getMonthValue() >= mesInicioTA && diaFin.getMonthValue() <= mesFinTA) {
			precio = precio * 1.4;
		}

		return precio;

	}

	@GetMapping("anadirReserva/{id}")
	public String showHabReservadas(@PathVariable("id") Long id, Model model, @ModelAttribute("usuarioActual") Usuario usuario,
			@ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {
		Habitacion h = habitacionService.findOne(id);
		System.out.println(h);

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(reservaDeHabitacion.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(reservaDeHabitacion.getFechaFin(), formateoFecha);

		Reserva reserva = new Reserva(fechaInicio, fechaFin, calcularPrecio(model, h.getPrecio()));
		
		reserva.setHabitacion(h);
		reserva.setUsuario(LoginController.usuario);

		reservaService.save(reserva);

		 h.addReserva(reserva);
		 usuario.addReserva(reserva);

		return "redirect:/FilterUser/reservas";
	}

}
