package com.salesianostriana.pruebaproyecto.formbean;

public class ReservaDeHabitacion {

	private String fechaInicio;
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
	public String toString() {
		return "ReservaDeHabitacion [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoHab=" + tipoHab
				+ "]";
	}

}
