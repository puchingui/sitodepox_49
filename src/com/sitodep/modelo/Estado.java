package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Estado extends Identificable {

	@Required
	@Column(length=30)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
