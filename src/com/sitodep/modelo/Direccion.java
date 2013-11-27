package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Embeddable
public class Direccion {

	@Column(length=75)
	private String linea1;		//Calle y numero, P.O Box o casillero
	
	@Column(length=75)
	private String linea2;		//Apartamento, suite, unidad, edificio, piso, etc.
	
	@ManyToOne
	@DescriptionsList
	private Sector sector;
	
	@ManyToOne
	@DescriptionsList
	private Ciudad ciudad;

	@ManyToOne
	@DescriptionsList
	private Provincia provincia;
	
	@Stereotype("MEMO")
	private String referencia;

	public String getLinea1() {
		return linea1;
	}

	public void setLinea1(String linea1) {
		this.linea1 = linea1;
	}

	public String getLinea2() {
		return linea2;
	}

	public void setLinea2(String linea2) {
		this.linea2 = linea2;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
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

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
