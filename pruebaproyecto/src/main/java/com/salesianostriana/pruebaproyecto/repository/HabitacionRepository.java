package com.salesianostriana.pruebaproyecto.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.pruebaproyecto.model.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
	
	@Query(nativeQuery=true, value="SELECT HABITACION.ID, HABITACION.PRECIO, TIPOHAB FROM HABITACION LEFT JOIN RESERVA ON (HABITACION.ID = RESERVA.HABITACION_ID) WHERE (?1 NOT BETWEEN FECHAINICIO AND FECHAFIN AND ?2 NOT BETWEEN FECHAINICIO AND FECHAFIN AND NOT(?1  <= FECHAINICIO AND ?2  >= FECHAFIN) AND TIPOHAB LIKE ?3) OR FECHAINICIO IS NULL")
	Iterable<Habitacion> findHabitacionesNoReservadas(LocalDate inicio, LocalDate fin, String tipoHabitacion);
	
	@Query(nativeQuery=true, value="SELECT HABITACION.TIPOHAB, RESERVA.FECHAINICIO, RESERVA.FECHAFIN, RESERVA.PRECIO, USUARIO.ID FROM HABITACION JOIN RESERVA  ON (HABITACION.ID = RESERVA.HABITACION_ID) JOIN USUARIO ON (RESERVA.USUARIO_ID = USUARIO.ID) WHERE USUARIO.ID = ?1")
	Iterable<Habitacion> findHabitacionesReservadasPorMiUsuario(Long id_usuario);
	

}
