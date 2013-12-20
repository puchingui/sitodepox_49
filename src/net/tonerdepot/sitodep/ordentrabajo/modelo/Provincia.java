package net.tonerdepot.sitodep.ordentrabajo.modelo;

import java.util.*;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

import org.openxava.annotations.*;

@Entity
public class Provincia extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String nombre;

	@OneToMany(mappedBy="provincia")
	private Collection<Ciudad> ciudades;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(Collection<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

}
