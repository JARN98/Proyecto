package com.salesianostriana.goldtowerhotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Habitacion {
	@Id
	@GeneratedValue
	private Long id;
	private double precioHabTA;
	private double precioHabTB;
	private String tipoHab;

	@OneToOne
	private Reserva reserva;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Habitacion(double precioHabTA, double precioHabTB, String tipoHab) {
		this.precioHabTA = precioHabTA;
		this.precioHabTB = precioHabTB;
		this.tipoHab = tipoHab;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTipoHab() {
		return tipoHab;
	}

	public void setTipoHab(String tipoHab) {
		this.tipoHab = tipoHab;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precioHabTA);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precioHabTB);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reserva == null) ? 0 : reserva.hashCode());
		result = prime * result + ((tipoHab == null) ? 0 : tipoHab.hashCode());
		return result;
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
		if (Double.doubleToLongBits(precioHabTA) != Double.doubleToLongBits(other.precioHabTA))
			return false;
		if (Double.doubleToLongBits(precioHabTB) != Double.doubleToLongBits(other.precioHabTB))
			return false;
		if (reserva == null) {
			if (other.reserva != null)
				return false;
		} else if (!reserva.equals(other.reserva))
			return false;
		if (tipoHab == null) {
			if (other.tipoHab != null)
				return false;
		} else if (!tipoHab.equals(other.tipoHab))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", precioHabTA=" + precioHabTA + ", precioHabTB=" + precioHabTB + ", tipoHab="
				+ tipoHab + ", reserva=" + reserva + "]";
	}

}
