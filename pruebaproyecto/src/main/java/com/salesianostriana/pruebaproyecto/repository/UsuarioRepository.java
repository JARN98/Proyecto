package com.salesianostriana.pruebaproyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.pruebaproyecto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Usuario findFirstByNombreAndContrasena(String nombre, String contrasena);
	
	public Usuario findFirtsByEmail(String email);
}
