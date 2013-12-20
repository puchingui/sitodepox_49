package net.tonerdepot.sitodep.ordentrabajo.modelo;

import javax.persistence.*;

@Entity
public class Estado {

	@Id
	@Column(length=30)
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
