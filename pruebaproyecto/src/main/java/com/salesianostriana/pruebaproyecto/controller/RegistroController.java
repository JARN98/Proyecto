package com.salesianostriana.pruebaproyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.formbean.RegistroDeUsuario;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@Controller
public class RegistroController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	@GetMapping("/Register")
	public String showRegister(Model model) {
		model.addAttribute("registroUsuario", new RegistroDeUsuario());
		return "Register";
	}

	@PostMapping("/checkRegister")
	public String doRegister(@ModelAttribute("registroUsuario") RegistroDeUsuario registroDeUsuario,
			BindingResult bindingResult, Model model) {
		Usuario usuario = usuarioService.registro(registroDeUsuario.getEmail());

		if (usuario == null) {
			
			usuario = new Usuario(false, registroDeUsuario.getNombre(), registroDeUsuario.getApellidos(), registroDeUsuario.getEmail(), registroDeUsuario.getContrasena());
			
			usuarioService.save(usuario);

			session.setAttribute("usuarioActual", usuario);
			
			return "redirect:/";
		} else {
			model.addAttribute("registerError", "El email ya ha sido registrado");
			return "Register";
		}

	}

}
