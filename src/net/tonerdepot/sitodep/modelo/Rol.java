package net.tonerdepot.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Rol extends Identificable {

	@Required
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
