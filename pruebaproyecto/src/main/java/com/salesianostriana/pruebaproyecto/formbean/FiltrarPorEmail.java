package com.salesianostriana.pruebaproyecto.formbean;

public class FiltrarPorEmail {

	private String email;

	public FiltrarPorEmail(String email) {
		super();
		this.email = email;
	}

	public FiltrarPorEmail() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "FiltrarPorEmail [email=" + email + "]";
	}

}
