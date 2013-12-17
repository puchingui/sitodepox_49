package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

/***
 * Representa un Sector de una Ciudad
 * @author Kenneth Burgos
 *
 */
@Entity
public class Sector extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String nombre;
	
	@ManyToOne
	@DescriptionsList
	private Ciudad ciudad;
	
	@ManyToOne
	@DescriptionsList
	private Provincia provincia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}
