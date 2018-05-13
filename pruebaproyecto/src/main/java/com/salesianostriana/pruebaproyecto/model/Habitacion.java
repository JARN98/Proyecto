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
	private double precio;

	@OneToMany
	Set<Reserva> listaReservas = new HashSet<Reserva>();

	public Set<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(Set<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Habitacion(String tipoHab, double precio) {
		this.tipoHab = tipoHab;
		this.precio = precio;
	}

	public Habitacion() {
		// TODO Auto-generated constructor stub
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listaReservas == null) ? 0 : listaReservas.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoHab == null) ? 0 : tipoHab.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", tipoHab=" + tipoHab + ", precio=" + precio + ", listaReservas="
				+ listaReservas + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitacion other = (Habitacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaReservas == null) {
			if (other.listaReservas != null)
				return false;
		} else if (!listaReservas.equals(other.listaReservas))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (tipoHab == null) {
			if (other.tipoHab != null)
				return false;
		} else if (!tipoHab.equals(other.tipoHab))
			return false;
		return true;
	}

	// Helper
	public void addReserva(Reserva r) {
		if (r != null) {
			r.setHabitacion(this);
			this.getListaReservas().add(r);
		}
	}

	public void removeReserva(Reserva r) {
		if (r != null) {
			r.setHabitacion(null);
			this.getListaReservas().remove(r);
		}
	}

}
