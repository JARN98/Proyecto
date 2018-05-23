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
import com.salesianostriana.pruebaproyecto.controller.LoginController;

@Controller
public class RegistroController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	@GetMapping("/Register")
	public String showRegister(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		model.addAttribute("registroUsuario", new RegistroDeUsuario());
		return "Register";
	}

	@PostMapping("/checkRegister")
	public String doRegister(@ModelAttribute("registroUsuario") RegistroDeUsuario registroDeUsuario,
			BindingResult bindingResult, Model model) {
		Usuario u = usuarioService.registro(registroDeUsuario.getEmail());

		if (u == null) {

			u = new Usuario(false, registroDeUsuario.getNombre(), registroDeUsuario.getApellidos(),
					registroDeUsuario.getEmail(), registroDeUsuario.getContrasena());

			usuarioService.save(u);

			session.setAttribute("usuarioActual", u);
			model.addAttribute("usuarioActual", u);
			LoginController.usuario = u;

			return "redirect:/";
		} else {
			model.addAttribute("registerError", "El email ya ha sido registrado");
			return "Register";
		}

	}

}
