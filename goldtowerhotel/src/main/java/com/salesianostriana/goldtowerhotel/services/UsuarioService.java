package com.salesianostriana.goldtowerhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.goldtowerhotel.model.Usuario;
import com.salesianostriana.goldtowerhotel.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	public Iterable<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Usuario save(Usuario u) {
		return repo.save(u);
	}

	public Usuario edit(Usuario u) {
		return repo.save(u);
	}

	public Usuario delete(Usuario u) {
		Usuario deleteUser = repo.findById(u.getId()).orElse(null);
		if (deleteUser != null)
			repo.delete(u);

		return deleteUser;
	}

}
