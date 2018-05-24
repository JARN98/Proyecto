package com.salesianostriana.pruebaproyecto.repository;

import java.time.LocalDate;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.pruebaproyecto.model.Habitacion;
import com.salesianostriana.pruebaproyecto.model.Reserva;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
	
	@Query(nativeQuery=true, value="SELECT * FROM HABITACION LEFT JOIN RESERVA ON (HABITACION.ID = RESERVA.HABITACION_ID) WHERE (:inicio NOT BETWEEN FECHAINICIO AND FECHAFIN AND :fin NOT BETWEEN FECHAINICIO AND FECHAFIN AND NOT(:inicio <= FECHAINICIO AND :fin  >= FECHAFIN) AND TIPOHAB LIKE :tipoHabitacion) OR FECHAINICIO IS NULL AND TIPOHAB LIKE :tipoHabitacion")
	Iterable<Habitacion> findHabitacionesNoReservadas(@Param("inicio") LocalDate inicio,@Param("fin") LocalDate fin, @Param("tipoHabitacion") String tipoHabitacion);
	
	@Query(nativeQuery=true, value="SELECT HABITACION.TIPOHAB, RESERVA.FECHAINICIO, RESERVA.FECHAFIN, RESERVA.PRECIO, USUARIO.ID FROM HABITACION JOIN RESERVA  ON (HABITACION.ID = RESERVA.HABITACION_ID) JOIN USUARIO ON (RESERVA.USUARIO_ID = USUARIO.ID) WHERE USUARIO.ID = ?1")
	Iterable<Habitacion> findHabitacionesReservadasPorMiUsuario(Long id_usuario);
	
	@Query(nativeQuery=true, value="SELECT * FROM HABITACION WHERE PRECIO = :precio")
	Iterable<Habitacion> buscarPorPrecio(@Param("precio") double precio);
	
	Iterable<Habitacion> findByTipoHabContainingIgnoreCase(String tipoHab);
	

}
