package com.salesianostriana.pruebaproyecto.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.formbean.LoginDeUsuario;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.security.SecurityConfiguration;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@Controller
public class LoginController {
	
	private boolean errorL = false;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	public static Usuario usuario;

	@GetMapping("/login")
	public String showLogin(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		model.addAttribute("loginUsuario", new LoginDeUsuario());
		if(errorL) {
			model.addAttribute("loginError", true);
		}
		
		if(SecurityConfiguration.error6) {
			model.addAttribute("Error6", true);
		}
		
		return "login";
	}

	@PostMapping("/checkLogin")
	public String doLogin(@ModelAttribute("loginDeUsuario") LoginDeUsuario loginDeUsuario, BindingResult bindingResult,
			Model model) {

		Usuario u = usuarioService.login(loginDeUsuario.getNombreUsuario(), loginDeUsuario.getContrasena());

		if (u != null) {
			session.setAttribute("usuarioActual", u);
			model.addAttribute("usuarioActual", u);
			usuario = u;
			errorL=false;
			return "redirect:/";
		} else {
			errorL=true;
			return "redirect:/login";
		}

	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		session.setAttribute("usuarioActual", null);
		usuario = null;
		return "redirect:/";
	}

}
