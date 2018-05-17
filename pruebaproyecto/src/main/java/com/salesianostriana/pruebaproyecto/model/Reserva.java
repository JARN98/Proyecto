package com.salesianostriana.pruebaproyecto.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reserva {
	@GeneratedValue
	@Id
	private Long id;
	@DateTimeFormat
	private LocalDateTime fechaInicio;
	@DateTimeFormat
	private LocalDateTime fechaFin;
	private double precio;
	
	public Reserva() {
		
	}

	public Reserva(LocalDateTime fechaInicio, LocalDateTime fechaFin, double precio) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}

	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne
	private Habitacion habitacion;

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((habitacion == null) ? 0 : habitacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (habitacion == null) {
			if (other.habitacion != null)
				return false;
		} else if (!habitacion.equals(other.habitacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precio=" + precio
				+ ", usuario=" + usuario + ", habitacion=" + habitacion + "]";
	}

}
