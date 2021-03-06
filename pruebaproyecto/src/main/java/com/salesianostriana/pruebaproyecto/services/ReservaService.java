package com.salesianostriana.pruebaproyecto.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.salesianostriana.pruebaproyecto.model.Reserva;
import com.salesianostriana.pruebaproyecto.repository.ReservaRepository;

@Service
public class ReservaService {
	@Autowired
	ReservaRepository repo;

	public Iterable<Reserva> listaDeReservasDelUsuario(Long id) {
		return repo.findHabitacionesReservadasPorMiUsuario(id);
	}

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

	/*
	 * Método para que al elegir fecha de inicio y de salida junto al precio de la
	 * habitación por noche se calcule el precio final que costará la reserva,
	 * funciona multiplicando el precio de la habitación por el número de dias y se
	 * le añade un 40% si alguno de las fechas está dentro del periodo de Temporada
	 * Alta.
	 */
	public double calcularPrecio(Model model, double precio, String fechaInicio, String fechaFin) {

		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate diaInicio = LocalDate.parse(fechaInicio, formateoFecha);

		LocalDate diaFin = LocalDate.parse(fechaFin, formateoFecha);

		Long dias = ChronoUnit.DAYS.between(diaInicio, diaFin);

		precio = precio * dias;

		int mesInicioTA = 6;
		int mesFinTA = 7;
		if (diaInicio.getMonthValue() >= mesInicioTA || diaFin.getMonthValue() <= mesFinTA) {
			precio = precio * 1.4;
		}

		return precio;

	}

}
