package net.tonerdepot.sitodep.ordentrabajo.modelo;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

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
