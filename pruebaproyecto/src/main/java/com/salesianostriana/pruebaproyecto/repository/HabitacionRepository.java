package com.salesianostriana.pruebaproyecto.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.pruebaproyecto.model.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

	public Iterable<Habitacion> findReservaFechaInicioAfterIsNullAndReservaFechaFinBeforeIsNull(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
