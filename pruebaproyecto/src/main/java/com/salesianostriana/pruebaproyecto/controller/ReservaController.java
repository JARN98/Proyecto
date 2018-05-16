package com.salesianostriana.pruebaproyecto.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.pruebaproyecto.formbean.ReservaDeHabitacion;
import com.salesianostriana.pruebaproyecto.services.ReservaService;

@Controller
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/FilterUser/reservas")
	public String showReserva(Model model) {
		model.addAttribute("reserva", new ReservaDeHabitacion());
		return "FilterUser/reservas";
	}
	
//	public double calcularPrecio(Model model) {
//		double precioFinal;
//		
//		ReservaDeHabitacion reserva = (ReservaDeHabitacion) session.getAttribute("reserva");
//		
//		LocalDateTime diaInicio = reserva.getFechaInicio();
//		
//		LocalDateTime diaFin = reserva.getFechaFin();
//		
//	}
	
//	@PostMapping("/FilterUser/consulta")
//	public String doConsulta() {
//		//Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
//		
//		
//	}

}
