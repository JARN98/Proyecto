package com.salesianostriana.pruebaproyecto.controller;

import java.util.HashSet;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.HabitacionService;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;
import com.salesianostriana.pruebaproyecto.utility.Pager;

@Controller
public class IndexController {

	// @Autowired
	// private HttpSession session;

	@Autowired
	private HabitacionService habitacionService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@GetMapping({ "/", "/index" })
	public String showIndex(Model model, @ModelAttribute("usuarioActual") Usuario usuario) {
		// Usuario u = (Usuario) session.getAttribute("usuarioActual");
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
	public String showContact() {
		return "contact";
	}

	@GetMapping("/suites")
	public String showSuites() {
		return "suites";
	}

	@GetMapping("/diamante")
	public String showSuite1() {
		return "diamante";
	}

	@GetMapping("/esmeralda")
	public String showSuite2() {
		return "esmeralda";
	}

	@GetMapping("/oro")
	public String showSuite3() {
		return "oro";
	}

	@GetMapping("/hotel")
	public String showHotel() {
		return "hotel";
	}

	@GetMapping("/usuarios")
	public String showHabitacion(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {
    	// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
    	// el tamaño de página inicial.
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        
        // Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
        // que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
        // del parámetro decrementado en 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        // Obtenemos la página definida por evalPage y evalPageSize de objetos de nuestro modelo
        Page<Habitacion> persons = habitacionService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        // Creamos el objeto Pager (paginador) indicando los valores correspondientes.
        // Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos botones
        // debe mostrar y cuál es el número de objetos a dibujar.
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
        
        model.addAttribute("persons", persons);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);

		model.addAttribute("crearHabitacion", new Habitacion());
		Iterable<Habitacion> lista = new HashSet<Habitacion>();
		lista = habitacionService.findAll();
		model.addAttribute("listaHabitaciones", lista);

		Iterable<Usuario> listaU = new HashSet<Usuario>();
		listaU = usuarioService.findAll();
		model.addAttribute("listaUsuarios", listaU);
		model.addAttribute("u", new Usuario());
		model.addAttribute("usuarioAEditar", new Usuario());
		return "usuarios";
	}

}
