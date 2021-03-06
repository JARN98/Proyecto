package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.pruebaproyecto.formbean.CrearHabitacion;
import com.salesianostriana.pruebaproyecto.formbean.FiltrarPorEmail;
import com.salesianostriana.pruebaproyecto.formbean.FiltrarPorTipoHab;
import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;
import com.salesianostriana.pruebaproyecto.utility.Pager;

@Controller
public class IndexController {

	@Autowired
	private HabitacionService habitacionService;

	@Autowired
	private UsuarioService usuarioService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@GetMapping({ "/", "/index" })
	public String showIndex(Model model, @ModelAttribute("usuarioActual") Usuario usuario) {
		/*
		 * Esto sirve para que en el nav salgan distintas cosas si estás logueado o no y
		 * si el usuario que está logueado sea administrador o no.
		 */
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}

		model.addAttribute("usuarioRegistrado", usuario);
		return "index";
	}

	@GetMapping("/contact")
	public String showContact(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "contact";
	}

	@GetMapping("/suites")
	public String showSuites(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "suites";
	}

	@GetMapping("/diamante")
	public String showSuite1(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "diamante";
	}

	@GetMapping("/esmeralda")
	public String showSuite2(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "esmeralda";
	}

	@GetMapping("/oro")
	public String showSuite3(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "oro";
	}

	@GetMapping("/hotel")
	public String showHotel(Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		return "hotel";
	}

	@GetMapping("/usuarios")
	public String showHabitacion(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Habitacion> persons = habitacionService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("persons", persons);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		model.addAttribute("crearHabitacion", new CrearHabitacion());
		Iterable<Habitacion> lista = new HashSet<Habitacion>();
		lista = habitacionService.findAll();
		model.addAttribute("listaHabitaciones", lista);

		// error 4
		model.addAttribute("Error4", HabitacionController.error4);

		model.addAttribute("usuarioOrdenado", new FiltrarPorEmail());

		model.addAttribute("habitacionOrdenada", new FiltrarPorTipoHab());

		Iterable<Usuario> listaU = new HashSet<Usuario>();
		listaU = usuarioService.findAll();
		model.addAttribute("listaUsuarios", listaU);
		model.addAttribute("u", new Usuario());
		model.addAttribute("usuarioAEditar", new Usuario());
		return "usuarios";
	}

	@PostMapping("/filtradoUsuarios")
	public String filtradoUsuarios(@ModelAttribute("usuarioOrdenado") FiltrarPorEmail email, Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		Iterable<Usuario> listaUOrd = usuarioService.buscarPorEmail(email.getEmail());
		model.addAttribute("listaOrdenada", listaUOrd);
		return "/FiltradoUsuarios";
	}

	@PostMapping("/filtradoHabitacion")
	public String filtradoHabitacion(@ModelAttribute("habitacionOrdenada") FiltrarPorTipoHab tipoHab, Model model) {
		if (LoginController.usuario == null) {
			model.addAttribute("noUsuario", true);
		}
		if (LoginController.usuario != null) {
			model.addAttribute("usuario", true);
			if (LoginController.usuario.isAdmin()) {
				model.addAttribute("panelAdmin", true);
			}
		}
		/*
		 * Esto es un ejemplo de búsqueda por consulta utilizando sql.
		 */
		Iterable<Habitacion> listaHOrd = habitacionService.habitacionesPorTipohab(tipoHab.getTipoHab());
		model.addAttribute("listaHabitacionesO", listaHOrd);
		return "/FiltradoHabitaciones";
	}

	@GetMapping("/loginE")
	public String loginE(Model model) {
		model.addAttribute("loginErrorReserva", true);
		return "/login";
	}

}
