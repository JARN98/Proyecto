package com.salesianostriana.pruebaproyecto.formbean;

public class CrearHabitacion {
	private String precio;
	private String tipoHab;

	public CrearHabitacion(String precio, String tipoHab) {
		super();
		this.precio = precio;
		this.tipoHab = tipoHab;
	}

	public CrearHabitacion() {
		super();
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(String tipoHab) {
		this.tipoHab = tipoHab;
	}

	@Override
	public String toString() {
		return "CrearHabitacion [precio=" + precio + ", tipoHab=" + tipoHab + "]";
	}

}
