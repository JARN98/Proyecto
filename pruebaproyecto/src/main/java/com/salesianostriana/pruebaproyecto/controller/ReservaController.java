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
		//th:if
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
		
		//Formateo de fechas

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(r.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(r.getFechaFin(), formateoFecha);
		
		model.addAttribute("precioFinal", reservaService.calcularPrecio(model, 1, r.getFechaInicio(), r.getFechaFin()));
		

		Iterable<Habitacion> habitacionesQueSePuedenReservar = habitacionService
				.findHabitacionesNoReservadas(fechaInicio, fechaFin, r.getTipoHab());

		reservaDeHabitacion = new ReservaDeHabitacion(r.getFechaInicio(), r.getFechaFin(), r.getTipoHab());
		
		//Filtro

		model.addAttribute("listaPorPrecio", new FiltrarPorPrecio());
		
		//Errores

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
		
		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicio = LocalDate.parse(nuevar.getFechaInicio(), formateoFecha);
		LocalDate fechaFin = LocalDate.parse(nuevar.getFechaFin(), formateoFecha);
		
		double precio1 = precio.getPrecio();
		List<Habitacion> habitacionesFiltradas = (List<Habitacion>) habitacionService
				.findHabitacionesNoReservadas(fechaInicio, fechaFin, nuevar.getTipoHab());
		List<Habitacion> filtroHab = habitacionesFiltradas.stream().filter((p) -> p.getPrecio() == precio1).collect(Collectors.toList());
		model.addAttribute("listaPrecio", filtroHab);
		return "/FilterUser/habitacionesReserva2";
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
