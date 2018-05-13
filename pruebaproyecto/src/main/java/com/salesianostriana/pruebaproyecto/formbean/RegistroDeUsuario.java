package com.salesianostriana.pruebaproyecto.formbean;

public class RegistroDeUsuario {
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasena;

	public RegistroDeUsuario() {

	}

	public RegistroDeUsuario(String nombre, String apellidos, String email, String contrasena) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "RegistroDeUsuario [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", contrasena="
				+ contrasena + "]";
	}

}
