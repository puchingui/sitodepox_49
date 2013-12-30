package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity(name="net.tonerdepot.sitodep.modelo.Provincia")
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
