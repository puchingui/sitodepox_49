package net.tonerdepot.sitodep.modelo;

import javax.persistence.*;

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
