package com.salesianostriana.pruebaproyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.services.UsuarioService;

@SpringBootApplication
public class PruebaproyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaproyectoApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertInitialData(UsuarioService service) {
		return args -> {
			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setNombre("jose");
			nuevoUsuario.setContrasena("1234");
			service.save(nuevoUsuario);
		};
	}
}
