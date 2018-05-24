package com.salesianostriana.pruebaproyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesianostriana.pruebaproyecto.model.Usuario;
import com.salesianostriana.pruebaproyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repositorio;

	public Usuario registro(String email) {
		return repositorio.findFirstByEmail(email);
	}

	public Usuario login(String nombre, String contrasena) {
		return repositorio.findFirstByNombreAndContrasena(nombre, contrasena);
	}

	public Usuario findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Iterable<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Usuario save(Usuario usuario) {
		return repositorio.save(usuario);
	}

	public Usuario edit(Usuario u) {
		return repositorio.save(u);
	}

	public Usuario delete(Usuario u) {
		Usuario deleteUsu = repositorio.findById(u.getId()).orElse(null);
		if (deleteUsu != null)
			repositorio.delete(u);

		return deleteUsu;
	}

	public Page<Usuario> findAllPageable(Pageable pageable) {
		return repositorio.findAll(pageable);
	}
	
	public Iterable<Usuario> buscarPorEmail(String email){
		return repositorio.findByEmailContainingIgnoreCase(email);
	}
}
