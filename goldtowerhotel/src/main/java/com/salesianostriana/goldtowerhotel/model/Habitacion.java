package com.salesianostriana.goldtowerhotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Habitacion {
	@GeneratedValue
	@Id
	private Long id;
	private String tipoHab;
	private double precioHabTA;
	private double precioHabTB;

	@OneToOne
	private Reserva reserva;

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Habitacion() {

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

	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", tipoHab=" + tipoHab + ", precioHabTA=" + precioHabTA + ", precioHabTB="
				+ precioHabTB + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(precioHabTA);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precioHabTB);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(precioHabTA) != Double.doubleToLongBits(other.precioHabTA))
			return false;
		if (Double.doubleToLongBits(precioHabTB) != Double.doubleToLongBits(other.precioHabTB))
			return false;
		if (tipoHab == null) {
			if (other.tipoHab != null)
				return false;
		} else if (!tipoHab.equals(other.tipoHab))
			return false;
		return true;
	}
	
	// Helper
	
	/*public void addReserva(Reserva r) {
		if( r != null) {
			r.setHabitacion(this);
			this.getReserva().add
		}
	}*/

}
