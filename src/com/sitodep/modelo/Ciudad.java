package com.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
public class Ciudad extends Identificable {

	@Required
	@Column(length=64, unique=true)
	private String nombre;
	
	@ManyToOne
	@DescriptionsList
	private Provincia provincia;
	
	@OneToMany(mappedBy="ciudad")
	private Collection<Sector> sectores;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
