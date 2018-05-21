package com.salesianostriana.pruebaproyecto.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.repository.HabitacionRepository;

@Service
public class HabitacionService {
	
	@Autowired
	HabitacionRepository repositorio;
	
	public Iterable<Habitacion> findHabitacionesNoReservadas(LocalDate inicio, LocalDate fin, String tipoHab){
		return repositorio.findHabitacionesNoReservadas(inicio, fin, tipoHab);
	}

	public Habitacion findOne(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Iterable<Habitacion> findAll() {
		return repositorio.findAll();
	}

	public Habitacion save(Habitacion habitacion) {
		return repositorio.save(habitacion);
	}
	
	public Habitacion edit(Habitacion h) {
		return repositorio.save(h);
	}

	public Habitacion delete(Habitacion h) {
		Habitacion deleteHab = repositorio.findById(h.getId()).orElse(null);
		if (deleteHab != null)
			repositorio.delete(h);

		return deleteHab;
	}
	
	
}
