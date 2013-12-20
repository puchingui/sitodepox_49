package net.tonerdepot.sitodep.inventario.modelo;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

import org.openxava.annotations.*;

@Entity
public class Tipo extends Identificable {
	
	@Required
	@Column(length=50)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
