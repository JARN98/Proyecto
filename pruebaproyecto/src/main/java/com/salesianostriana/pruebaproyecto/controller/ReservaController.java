package com.salesianostriana.pruebaproyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.formbean.ReservaDeHabitacion;
import com.salesianostriana.pruebaproyecto.model.Habitacion;
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
	
	@PostMapping("/VerHabitaciones")
	public String showHab(Model model, @ModelAttribute("nuevaReserva") ReservaDeHabitacion r) {
		Iterable<Habitacion> habitacionesQueSePuedenReservar =  habitacionService.findListaHabitaciones(r.getFechaInicio(), r.getFechaFin());
		
		if(habitacionesQueSePuedenReservar != null ) {
			model.addAttribute("hPuedenReservarse", habitacionesQueSePuedenReservar);
			return "index";
		}else {
			model.addAttribute("ErrorNohayHab", "Lo siento, en esas fechas no tenemos ninguna de las suites seleccionada disponibles");
			return "index";
		}
		
	}
	
//	public double calcularPrecio(Model model, @ModelAttribute("nuevaReserva") Reserva reserva) {
//		double precioFinal = 0;
//		
//		
//		LocalDateTime diaInicio = reserva.getFechaInicio();
//		
//		LocalDateTime diaFin = reserva.getFechaFin();
//		
//		Long dias = ChronoUnit.DAYS.between(diaInicio, diaFin);
//	
//		String temporadaAlta = "06-01";
//		String temporadaBaja = "08-01";
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
//		
//		LocalDateTime fechaInicioTA = LocalDateTime.parse(temporadaAlta, formatter);
//		LocalDateTime fechaInicioTB = LocalDateTime.parse(temporadaBaja, formatter);
//		
//		if(diaInicio.isAfter(fechaInicioTA) && diaFin.isBefore(fechaInicioTB)) {
//			precioFinal = precioFinal * 1.4;
//		}
//		
//		
//		
//		
//		
//		
//		
//	}
	
//	@PostMapping("/FilterUser/consulta")
//	public String doConsulta() {
//		//Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
//		
//		
//	}

}
