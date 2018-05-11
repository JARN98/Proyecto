package com.salesianostriana.pruebaproyecto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@GeneratedValue
	@Id
	private Long id;
	private boolean admin;
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasena;
	
	@OneToMany
	Set<Reserva> listaReservas = new HashSet<Reserva>();
	

	public Set<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(Set<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	

	public Usuario(boolean admin, String nombre, String apellidos, String email, String contrasena) {
		this.admin = admin;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrasena = contrasena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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

}
