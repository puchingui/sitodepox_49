package net.tonerdepot.sitodep.inventario.modelo;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

import org.openxava.annotations.*;

@Entity
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
