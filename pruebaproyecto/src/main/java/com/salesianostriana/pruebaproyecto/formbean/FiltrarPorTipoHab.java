package com.salesianostriana.pruebaproyecto.formbean;

public class FiltrarPorTipoHab {
	private String tipoHab;

	public FiltrarPorTipoHab(String tipoHab) {
		super();
		this.tipoHab = tipoHab;
	}

	public FiltrarPorTipoHab() {

	}

	public String getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(String tipoHab) {
		this.tipoHab = tipoHab;
	}

	@Override
	public String toString() {
		return "FiltrarPorTipoHab [tipoHab=" + tipoHab + "]";
	}

}
