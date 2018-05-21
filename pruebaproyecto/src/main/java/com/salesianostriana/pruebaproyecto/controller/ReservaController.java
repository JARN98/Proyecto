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
		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(r.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(r.getFechaFin(), formateoFecha);

		System.out.println(fechaInicio);
		System.out.println(fechaFin);
		System.out.println(r.getTipoHab());

		Iterable<Habitacion> habitacionesQueSePuedenReservar = habitacionService
				.findHabitacionesNoReservadas(fechaInicio, fechaFin, r.getTipoHab());

		reservaDeHabitacion = new ReservaDeHabitacion(r.getFechaInicio(), r.getFechaFin(), r.getTipoHab());

		if (fechaInicio.isAfter(fechaFin)) {
			model.addAttribute("ErrorFecha1", "No puede poner una fecha de fin anterior a la de inicio");
			return "/FilterUser/reservas";
		} else if (fechaInicio.isBefore(LocalDate.now())) {
			model.addAttribute("ErrorFecha2", "No puede poner una fecha anterior a la de hoy");
			return "/FilterUser/reservas";
		} else if (habitacionesQueSePuedenReservar == null) {
			model.addAttribute("ErrorNohayHab",
					"Lo siento, en esas fechas no tenemos ninguna de las suites seleccionada disponibles");
			return "/FilterUser/reservas";
		} else {
			model.addAttribute("hPuedenReservarse", habitacionesQueSePuedenReservar);
			return "FilterUser/habitacionesReserva";
		}

	}

	@GetMapping("anadirReserva/{id}")
	public String showHabReservadas(@PathVariable("id") Long id, Model model,
			@ModelAttribute("usuarioActual") Usuario usuario, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {
		Habitacion h = habitacionService.findOne(id);

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(reservaDeHabitacion.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(reservaDeHabitacion.getFechaFin(), formateoFecha);

		Reserva reserva = new Reserva(fechaInicio, fechaFin, reservaService.calcularPrecio(model, h.getPrecio(),
				reservaDeHabitacion.getFechaInicio(), reservaDeHabitacion.getFechaFin()));

		reserva.setHabitacion(h);
		reserva.setUsuario(LoginController.usuario);

		reservaService.save(reserva);

		h.addReserva(reserva);
		usuario.addReserva(reserva);

		return "redirect:/FilterUser/reservas";

	}

}
