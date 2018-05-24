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
			nuevoUsuario.setNombre("admin");
			nuevoUsuario.setContrasena("admin");
			nuevoUsuario.setAdmin(true);
			service.save(nuevoUsuario);
			
			Usuario usuario = new Usuario();
			usuario.setNombre("usuario");
			usuario.setEmail("arjones@gmail.com");
			usuario.setContrasena("1234");
			service.save(usuario);
		};
	}
}
