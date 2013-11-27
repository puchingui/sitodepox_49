package com.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Provincia extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String provincia;

	@OneToMany(mappedBy="provincia")
	private Collection<Ciudad> ciudades;
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Collection<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(Collection<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

}
