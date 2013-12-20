package net.tonerdepot.sitodep.inventario.modelo;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

@Entity
public class Motivo extends Identificable {

	@Column(length=32, unique=true)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
