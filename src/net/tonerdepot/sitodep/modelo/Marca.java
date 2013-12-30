package net.tonerdepot.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity(name="net.tonerdepot.sitodep.modelo.Marca")
public class Marca extends Identificable {
	
	@Required
	@Column(length=50)
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
