package com.sitodep.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Tab(properties="serial, tipo.descripcion, marca.nombre, modelo")
@View(name="Simple", members="serial, tipo, marca, modelo")
public class Equipo {
	
	@Id
	@Column(length=32)
	private String serial;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Tipo tipo;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Marca marca;
	
	@Column(length=30)
	@Required
	private String modelo;
	
	@Column(length=50)
	private String description;
	
	@Stereotype("PHOTO")
	private byte[] foto;
	
	@Stereotype("IMAGES_GALLERY")
	@Column(length=32)
	private String masFotos;
	
	@Stereotype("MEMO")
	private String observacion;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getMasFotos() {
		return masFotos;
	}

	public void setMasFotos(String masFotos) {
		this.masFotos = masFotos;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}