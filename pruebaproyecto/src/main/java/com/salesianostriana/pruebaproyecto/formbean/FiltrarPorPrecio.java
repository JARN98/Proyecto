package com.salesianostriana.pruebaproyecto.formbean;

public class FiltrarPorPrecio {
	private double precio;

	public FiltrarPorPrecio(double precio) {
		super();
		this.precio = precio;
	}

	public FiltrarPorPrecio() {

	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "FiltrarPorPrecio [precio=" + precio + "]";
	}

}
