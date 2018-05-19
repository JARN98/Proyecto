package com.salesianostriana.pruebaproyecto.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.pruebaproyecto.model.Habitacion;

public interface ConsultaRepository extends JpaRepository<Long, Habitacion>{
	
//	@Query("SELECT h FROM HABITACION h JOIN RESERVA r ON (h.ID = r.HABITACION_ID) WHERE ?1 NOT BETWEEN r.FECHAINICIO AND r.FECHAFIN AND ?2 NOT BETWEEN r.FECHAINICIO r.AND FECHAFIN AND h.TIPOHAB LIKE ?3;")
//	Iterable<Habitacion> findHabitacionesNoReservadas(LocalDate fechaInicio, LocalDate fechaFin, String tipoHab);
}
