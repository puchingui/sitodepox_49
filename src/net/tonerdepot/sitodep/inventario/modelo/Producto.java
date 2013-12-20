package net.tonerdepot.sitodep.inventario.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import net.tonerdepot.sitodep.jpa.*;

@Entity
@Tab(properties="serial, tipo.descripcion, marca.nombre, modelo, ubicacion.nombre")
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
	
	@ManyToOne
	@DescriptionsList(descriptionProperties="nombre")
	private Ubicacion ubicacion;
	
	@OneToMany(mappedBy="producto")
	@CollectionView("NoProducto")
	@ListAction("ManyToMany.new")
	@ListProperties("conduce, codigo, cliente.nombre, motivo.descripcion")
	private Collection<Movimiento> movimientos;

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

	public Collection<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Collection<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
}
