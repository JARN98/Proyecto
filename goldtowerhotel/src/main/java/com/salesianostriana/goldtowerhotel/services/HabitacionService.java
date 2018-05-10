package com.salesianostriana.goldtowerhotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.goldtowerhotel.model.Habitacion;
import com.salesianostriana.goldtowerhotel.repository.HabitacionRepository;

@Service
public class HabitacionService {
	
	@Autowired
	private HabitacionRepository repo;
	
	public Iterable<Habitacion> findAll() {
		return repo.findAll();
	}
	
	public Habitacion findOne(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public Habitacion save(Habitacion h) {
		return repo.save(h);
	}
	
	public Habitacion edit(Habitacion h) {
		return repo.save(h);
	}
	
	public Habitacion delete(Habitacion h) {
		Habitacion deleteHab = repo.findById(h.getId()).orElse(null);
		if (deleteHab != null)
			repo.delete(h);
		
		return deleteHab;
	}
	
	
}
