package com.salesianostriana.goldhoteltower.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.goldtowerhotel.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findFirstByNombreAndContrasena(String nombre, String contrasena);
}
