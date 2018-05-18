package com.salesianostriana.pruebaproyecto.formbean;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservaDeHabitacion {

	@DateTimeFormat
	private String fechaInicio;
	@DateTimeFormat
	private String fechaFin;
	private String tipoHab;

	public ReservaDeHabitacion(String fechaInicio, String fechaFin, String tipoHab) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoHab = tipoHab;
	}

	public ReservaDeHabitacion() {

	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(String tipoHab) {
		this.tipoHab = tipoHab;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((tipoHab == null) ? 0 : tipoHab.hashCode());
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
		ReservaDeHabitacion other = (ReservaDeHabitacion) obj;
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
		if (tipoHab == null) {
			if (other.tipoHab != null)
				return false;
		} else if (!tipoHab.equals(other.tipoHab))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservaDeHabitacion [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoHab=" + tipoHab
				+ "]";
	}

}
