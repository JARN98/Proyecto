package com.salesianostriana.pruebaproyecto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.pruebaproyecto.formbean.FiltrarPorPrecio;
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

	private ReservaDeHabitacion nuevar;

	public ReservaDeHabitacion reservaDeHabitacion;

	@GetMapping("/FilterUser/reservas")
	public String showReserva(Model model) {
		/*
		 * Esto sirve para que en el nav salgan distintas cosas si estás logueado o no y
		 * si el usuario que está logueado sea administrador o no.
		 */
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		model.addAttribute("nuevaReserva", new ReservaDeHabitacion());
		return "FilterUser/reservas";
	}

	@PostMapping("/habitacionesReserva")
	public String showHab(Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r,
			@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page) {
		// th:if
		nuevar = r;
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}

		// Formateo de fechas

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(r.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(r.getFechaFin(), formateoFecha);

		model.addAttribute("precioFinal", reservaService.calcularPrecio(model, 1, r.getFechaInicio(), r.getFechaFin()));

		Iterable<Habitacion> habitacionesQueSePuedenReservar = habitacionService
				.findHabitacionesNoReservadas(fechaInicio, fechaFin, r.getTipoHab());

		reservaDeHabitacion = new ReservaDeHabitacion(r.getFechaInicio(), r.getFechaFin(), r.getTipoHab());

		// Filtro

		model.addAttribute("listaPorPrecio", new FiltrarPorPrecio());

		// Errores

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

	@PostMapping("/habitacionesReservaPrecio")
	public String habitacionesPorPrecio(@ModelAttribute("listaPorPrecio") FiltrarPorPrecio precio, Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(nuevar.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(nuevar.getFechaFin(), formateoFecha);
		/*
		 * Esto lo hago para que cuando ponga el usuario en un campo donde va un double,
		 * un string no de error. (Sé que poniendo un type="number" en el input no te
		 * dejaría poner letras, lohago para demostrar que sé hacerlo).
		 */
		double precio1;
		try {
			precio1 = Double.parseDouble(precio.getPrecio());
		} catch (NumberFormatException n) {
			precio1 = 0;
		}

		List<Habitacion> habitacionesFiltradas = (List<Habitacion>) habitacionService
				.findHabitacionesNoReservadas(fechaInicio, fechaFin, nuevar.getTipoHab());
		double precio2 = precio1;
		/*
		 * Esto es una búsqueda por precio la cual está hecha por un filtrado de la
		 * clase stream utilizando expresión lambda, en el proyecto hay dos tipos de
		 * búsqueda, por filter y por consulta, hechas queriendo para mostrar la
		 * utilización tanto de java 8 como de consultas sql de base de datos.
		 */
		List<Habitacion> filtroHab = habitacionesFiltradas.stream().filter((p) -> p.getPrecio() == precio2)
				.collect(Collectors.toList());
		model.addAttribute("listaPrecio", filtroHab);
		return "/FilterUser/habitacionesReserva2";
	}

	@GetMapping("/FilterUser/resumenReserva/{id}")
	public String showSummary(@PathVariable("id") Long id, Model model,
			@ModelAttribute("usuarioActual") Usuario usuario, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {

		Habitacion h = habitacionService.findOne(id);

		model.addAttribute("habitacionId", h.getId());
		model.addAttribute("habitacionTipo", h.getTipoHab());
		model.addAttribute("inicio", reservaDeHabitacion.getFechaInicio());
		model.addAttribute("final", reservaDeHabitacion.getFechaFin());
		model.addAttribute("precioFinal", reservaService.calcularPrecio(model, 1, reservaDeHabitacion.getFechaInicio(),
				reservaDeHabitacion.getFechaFin()));
		model.addAttribute("precioHab", h.getPrecio());

		return "/FilterUser/resumenReserva";
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
