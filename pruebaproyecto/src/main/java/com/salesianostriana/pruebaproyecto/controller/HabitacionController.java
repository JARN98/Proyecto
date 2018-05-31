package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.formbean.CrearHabitacion;
import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;

@Controller
public class HabitacionController {
	
	public static boolean error4;

	@Autowired
	private HabitacionService habitacionService;

	@PostMapping("/registerHabitacion")
	public String doHabitacion(Model model,@ModelAttribute("crearHabitacion") CrearHabitacion h2form,
			@ModelAttribute("listaHabitaciones") HashSet<Habitacion> listaHabitaciones) {
		
		Habitacion h2 = new Habitacion();
		h2.setTipoHab(h2form.getTipoHab());
		double price = 0;
		try {
			price = Double.parseDouble(h2form.getPrecio());
		}catch(NumberFormatException n) {
			price = 0;
		}
		h2.setPrecio(price);
		
		
		
		if(h2.getPrecio() > 0 && h2.getPrecio() < 100000000) {
			habitacionService.save(h2);
			listaHabitaciones.add(h2);
			error4 = false;
		}else {
			error4 = true;
		}
		

		return "redirect:/usuarios#listaHabitaciones";
	}

	@GetMapping("usuarios/#listaHabitacion")
	public String showListH(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		
		model.addAttribute("error4", error4);
		return "usuarios#listaHabitacion";
	}

}
