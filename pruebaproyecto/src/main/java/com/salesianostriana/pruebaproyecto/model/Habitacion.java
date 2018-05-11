package com.salesianostriana.pruebaproyecto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Habitacion {

	@GeneratedValue
	@Id
	private Long id;
	private String tipoHab;
	private double precioHabTA;
	private double precioHabTB;

	@OneToMany
	Set<Reserva> listaReservas = new HashSet<Reserva>();

	public Set<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(Set<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Habitacion(String tipoHab, double precioHabTA, double precioHabTB) {
		this.tipoHab = tipoHab;
		this.precioHabTA = precioHabTA;
		this.precioHabTB = precioHabTB;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(String tipoHab) {
		this.tipoHab = tipoHab;
	}

	public double getPrecioHabTA() {
		return precioHabTA;
	}

	public void setPrecioHabTA(double precioHabTA) {
		this.precioHabTA = precioHabTA;
	}

	public double getPrecioHabTB() {
		return precioHabTB;
	}

	public void setPrecioHabTB(double precioHabTB) {
		this.precioHabTB = precioHabTB;
	}

}
