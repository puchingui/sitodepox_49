package com.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Tab(properties="serial, tipo.descripcion, marca.nombre, modelo, cliente.nombre")
@Views({
	@View(members="serial, tipo, marca, modelo; "
			+ "descripcion; "
			+ "activo;"
			+ "foto;"
			+ "masFotos;"
			+ "cliente;"
			+ "observacion;"
			+ "ordenes"),
	@View(name="ParaOrden", members="serial, tipo, marca, modelo; "
			+ "foto;"
			+ "masFotos")
})
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
	
	@Column(length=32)
	@Required
	private String modelo;
	
	@Column(length=64)
	private String descripcion;
	
	@Column(length=16)
	private String activo;
	
	@Stereotype("PHOTO")
	private byte[] foto;
	
	@Stereotype("IMAGES_GALLERY")
	@Column(length=32)
	private String masFotos;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("Simple")
	private Cliente cliente;
	
	@Stereotype("MEMO")
	private String observacion;
	
	@OneToMany(mappedBy="equipo")
	private Collection<OrdenTrabajo> ordenes;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Collection<OrdenTrabajo> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Collection<OrdenTrabajo> ordenes) {
		this.ordenes = ordenes;
	}
}