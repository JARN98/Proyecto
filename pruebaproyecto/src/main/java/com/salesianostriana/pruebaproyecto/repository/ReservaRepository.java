package com.salesianostriana.pruebaproyecto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.salesianostriana.pruebaproyecto.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Query(nativeQuery=true, value="SELECT * FROM HABITACION JOIN RESERVA ON (HABITACION.ID = RESERVA.HABITACION_ID) JOIN USUARIO ON (RESERVA.USUARIO_ID = USUARIO.ID) WHERE USUARIO.ID = ?1")
	Iterable<Reserva> findHabitacionesReservadasPorMiUsuario(Long id_usuario);
}
