package com.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Ciudad extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String ciudad;
	
	@ManyToOne
	@DescriptionsList(descriptionProperties="provincia")
	private Provincia provincia;
	
	@OneToMany(mappedBy="ciudad")
	private Collection<Sector> sectores;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Collection<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(Collection<Sector> sectores) {
		this.sectores = sectores;
	}
}
