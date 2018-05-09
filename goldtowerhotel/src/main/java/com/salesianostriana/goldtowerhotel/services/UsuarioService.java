package com.salesianostriana.goldtowerhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.goldhoteltower.repository.UsuarioRepository;
import com.salesianostriana.goldtowerhotel.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repositorio;

	public Usuario findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Iterable<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Usuario save(Usuario usuario) {
		return repositorio.save(usuario);
	}
}
