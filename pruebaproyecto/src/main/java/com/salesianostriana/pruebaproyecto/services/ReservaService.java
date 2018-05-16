package com.salesianostriana.pruebaproyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.repository.ReservaRepository;

@Service
public class ReservaService {
	@Autowired
	ReservaRepository repo;

	public Reserva findOne(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Iterable<Reserva> findAll() {
		return repo.findAll();
	}

	public Reserva save(Reserva r) {
		return repo.save(r);
	}
	
	public Reserva edit(Reserva r) {
		return repo.save(r);
	}

	public Reserva delete(Reserva r) {
		Reserva deleteRes = repo.findById(r.getId()).orElse(null);
		if (deleteRes != null)
			repo.delete(r);

		return deleteRes;
	}
	
	

}
