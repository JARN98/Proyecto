package com.salesianostriana.pruebaproyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.repository.HabitacionRepository;

@Service
public class HabitacionService {
	@Autowired
	HabitacionRepository repositorio;

	public Habitacion findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Iterable<Habitacion> findAll() {
		return repositorio.findAll();
	}

	public Habitacion save(Habitacion habitacion) {
		return repositorio.save(habitacion);
	}
	
	
}
