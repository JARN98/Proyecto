package com.salesianostriana.pruebaproyecto.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
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

	@Autowired
	private HabitacionService habitacionService;

	@GetMapping("/datos")
	public String showDatos(Model model, @ModelAttribute("usuarioActual") Usuario usuario) {
		Set<Reserva> listaR = usuario.getListaReservas();
		model.addAttribute("listaReservas", listaR);
		model.addAttribute("u", new Usuario());
		return "datos";
	}

	@GetMapping("/editarUsuario/{id}")
	public String showFormEditU(@PathVariable("id") Long id, Model model) {
		Usuario u = usuarioService.findOne(id);

		if (u == null) {
			return "redirect:/";
		} else {
			model.addAttribute("usuario", u);
			return "editarUsuario";
		}
	}

	@PostMapping("/edit")
	public String doEdit(@ModelAttribute("u") Usuario u) {
		Usuario usuarioAnterior = (Usuario) session.getAttribute("usuarioActual");

		u.setId(usuarioAnterior.getId());

		usuarioService.edit(u);
		return "datos";
	}

	@PostMapping("/editUserAdmin")
	public String doEditAdmin(@ModelAttribute Usuario usuario) {
		usuarioService.edit(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/editarHabitacion/{id}")
	public String showFormEditH(@PathVariable("id") Long id, Model model) {
		Habitacion h = habitacionService.findOne(id);

		if (h == null) {
			return "redirect:/";
		} else {
			model.addAttribute("habitacion", h);
			return "editarHabitacion";
		}
	}

	@PostMapping("/editRoomAdmin")
	public String doEditAdminH(@ModelAttribute Habitacion habitacion) {
		habitacionService.edit(habitacion);
		return "redirect:/usuarios";
	}

	@GetMapping("/eliminarHabitacion/{id}")
	public String deleteRoom(@PathVariable("id") Long id, Model model) {
		Habitacion h = habitacionService.findOne(id);
		habitacionService.delete(h);
		return "redirect:/usuarios";
	}

	@GetMapping("/eliminarUsuario/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		Usuario u = usuarioService.findOne(id);
		usuarioService.delete(u);
		return "redirect:/usuarios";

	}
}
