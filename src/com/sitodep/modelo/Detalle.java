package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Detalle extends Identificable {
	
	@ManyToOne
	private OrdenTrabajo padre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@ReferenceView("Simple")
	@NoFrame
	private Equipo equipo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@DescriptionsList
	private Estado estado;

	public OrdenTrabajo getPadre() {
		return padre;
	}

	public void setPadre(OrdenTrabajo padre) {
		this.padre = padre;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
