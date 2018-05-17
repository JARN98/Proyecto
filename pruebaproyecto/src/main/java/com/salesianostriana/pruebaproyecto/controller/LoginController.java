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
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("loginUsuario", new LoginDeUsuario());
		return "login";
	}

	@PostMapping("/checkLogin")
	public String doLogin(@ModelAttribute("loginDeUsuario") LoginDeUsuario loginDeUsuario, BindingResult bindingResult,
			Model model) {

		Usuario usuario = usuarioService.login(loginDeUsuario.getNombreUsuario(), loginDeUsuario.getContrasena());
		
		

		if (usuario != null) {
			session.setAttribute("usuarioActual", usuario);
			model.addAttribute("usuarioActual", usuario);
			return "redirect:/";
		} else {
			model.addAttribute("loginError", "El usuario o contraseña no es válido");
			return "login";
		}

	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		session.setAttribute("usuarioActual", null);
		return "redirect:/";
	}

}
