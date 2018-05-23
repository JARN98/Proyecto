package com.salesianostriana.pruebaproyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.pruebaproyecto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Usuario findFirstByNombreAndContrasena(String nombre, String contrasena);
	
	public Usuario findFirstByEmail(String email);
	
	@Query(nativeQuery=true, value="SELECT * FROM USUARIO WHERE EMAIL LIKE '%:email%'")
	public Iterable<Usuario> buscarPorEmail(@Param("email") String email);
}
