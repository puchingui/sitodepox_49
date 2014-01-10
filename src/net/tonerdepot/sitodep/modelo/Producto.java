package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Tab(properties="serial, tipo.descripcion, marca.nombre, modelo, ubicacion")
@View(name="Simple", members="serial, tipo, marca, modelo")
public class Producto extends Identificable {

	@Required
	@Column(length=32, unique=true)
	private String serial;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Tipo tipo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Marca marca;
	
	@Required
	@Column(length=32)
	private String modelo;
	
	@Enumerated(EnumType.STRING)
	private Ubicacion ubicacion;
	
	@OneToMany(mappedBy="producto")
	@CollectionView("NoProducto")
	@ListAction("ManyToMany.new")
	@ListProperties("conduce, codigo, fecha, cliente.nombre, motivo.descripcion, recibido")
	private Collection<Prestamo> prestamos;
	
	@OneToMany(mappedBy="producto")
	@CollectionView("NoProducto")
	@ListAction("ManyToMany.new")
	@ListProperties("fecha, tecnico.nombre, producto.serial, reporte")
	private Collection<Mantenimiento> mantenimientos;
	
	public enum Ubicacion {
		Almacen1,
		Almacen2,
		Taller,
		Prestado
	}

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

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Collection<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Collection<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public Collection<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(Collection<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}
}
