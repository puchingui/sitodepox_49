package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Sector extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String sector;
	
	@ManyToOne
	@DescriptionsList(descriptionProperties="ciudad")
	private Ciudad ciudad;
	
	@ManyToOne
	@DescriptionsList(descriptionProperties="provincia")
	private Provincia provincia;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
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
