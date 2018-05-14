package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.ReservaService;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@Controller
public class EditRemoveController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReservaService reservaService;

	@GetMapping("/datos")
	public String showDatos(Model model) {
		Iterable<Reserva> listaR = new HashSet<Reserva>();
		listaR = reservaService.findAll();
		model.addAttribute("listaReservas", listaR);
		model.addAttribute("u", new Usuario());
		return "datos";
	}

	@PostMapping("/edit")
	public String doEdit(@ModelAttribute("u") Usuario u) {
		Usuario usuarioAnterior = (Usuario) session.getAttribute("usuarioActual");

		u.setId(usuarioAnterior.getId());

		usuarioService.edit(u);
		return "datos";
	}
	
	@PostMapping("/editUserAdmin")
	public String doEditAdmin(@ModelAttribute("usuarioAEditar") Usuario u) {
		usuarioService.edit(u);
		return "redirect:/usuarios";
	}
}
