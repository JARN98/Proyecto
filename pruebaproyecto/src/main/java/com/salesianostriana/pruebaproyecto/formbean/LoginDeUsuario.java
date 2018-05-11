package com.salesianostriana.pruebaproyecto.formbean;

public class LoginDeUsuario {
	private String nombreUsuario;
	private String contrasena;

	public LoginDeUsuario() {

	}

	public LoginDeUsuario(String nombreUsuario, String contrasena) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
