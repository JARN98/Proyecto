package com.salesianostriana.pruebaproyecto.formbean;

public class FiltrarPorPrecio {
	private String precio;

	public FiltrarPorPrecio(String precio) {
		super();
		this.precio = precio;
	}
	
	public FiltrarPorPrecio() {
		
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "FiltrarPorPrecio [precio=" + precio + "]";
	}

}
