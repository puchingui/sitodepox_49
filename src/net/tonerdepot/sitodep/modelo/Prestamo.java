package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Tab(properties="conduce, fecha, cliente.nombre, motivo.descripcion, producto.serial, producto.marca.nombre, producto.modelo, recibido")
@Views({
	@View(members="Datos {conduce, fecha, recibido; departamento, motivo; cliente; producto} Recibo {reciboDePrestamo}"),
	@View(name="Simple", members="conduce; cliente; producto"),
	@View(name="NoProducto", members="Datos {conduce, fecha, recibido; departamento, motivo; cliente} Recibo {reciboDePrestamo}"),
	@View(name="NoCliente", members="Datos {conduce, fecha, recibido; departamento, motivo; producto} Recibo {reciboDePrestamo}")
})
public class Prestamo {

	@Id
	@Column(length=6)
	private String conduce;
	
	@ReadOnly
	private boolean recibido = false;
	
	@ManyToOne
	@ReferenceView("Simple")
	@NoCreate
	@NoModify
	private Cliente cliente;
	
	@Column(length=32)
	private String departamento;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	@NoModify
	@NoCreate
	private Motivo motivo;
	
	@ManyToOne
	@ReferenceView("Simple")
	@Required
	@SearchAction("Prestamo.buscarProducto")
	@NoModify
	private Producto producto;
	
	@OneToOne(mappedBy="prestamo")
	private ReciboDePrestamo reciboDePrestamo;
	
	public String getConduce() {
		return conduce;
	}

	public void setConduce(String conduce) {
		this.conduce = conduce;
	}

	public boolean isRecibido() {
		return recibido;
	}

	public void setRecibido(boolean recibido) {
		this.recibido = recibido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ReciboDePrestamo getReciboDePrestamo() {
		return reciboDePrestamo;
	}

	public void setReciboDePrestamo(ReciboDePrestamo reciboDePrestamo) {
		this.reciboDePrestamo = reciboDePrestamo;
	}

	@PrePersist
	public void cambiaUbicacionProducto() throws Exception {
		producto.setPrestado(true);
		producto.setUbicacion(Producto.Ubicacion.Prestado);
	}
}
