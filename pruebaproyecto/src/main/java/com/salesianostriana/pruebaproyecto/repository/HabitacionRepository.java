package com.salesianostriana.pruebaproyecto.repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.pruebaproyecto.model.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

//	@Query("SELECT h FROM HABITACION h LEFT JOIN HABITACION_LISTARESERVAS x (ON h.HABITACION.ID = x.HABITACION_LISTARESERVAS.HABITACION_ID) JOIN   WHERE h.HABITACION_ID IS NULL AND x.LISTARESERVAS_ID IS NULL;")
//	Iterable<Habitacion> findHabitacionesNoReservadas();
	//public Iterable<Habitacion> findListaReservasFechaInicioAfterAndListaReservasFechaFinBeforeAndTipoHabContaining(LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoHab);
//	@Query("SELECT h FROM HABITACION h JOIN HABITACION_LISTARESERVAS x ON (h.HABITACION.ID = x.HABITACION_LISTARESERVAS.HABITACION_ID) JOIN RESERVA r ON (x.HABITACION_LISTARESERVAS.LISTARESERVAS_ID = r.RESERVA.ID) WHERE TO_CHAR(r.FECHAINICIO, 'YYYY-MM-DD') BETWEEN ?1 AND ?2 OR TO_CHAR(r.FECHAFIN, 'YYYY-MM-DD') BETWEEN  ?1 AND  ?2")
//	Iterable<Habitacion> findHabitacionesNoReservadas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
//	@Query("SELECT h FROM HABITACION h JOIN HABITACION_LISTARESERVAS x ON (h.ID = x.HABITACION_ID) JOIN RESERVA r ON (x.LISTARESERVAS_ID = r.ID)")
//	Iterable<Habitacion> findHabitacionesNoReservadas();
}
