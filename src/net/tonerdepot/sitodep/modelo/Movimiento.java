package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity(name="net.tonerdepot.sitodep.modelo.Movimiento")
//@Tab(properties="conduce, codigo, fecha, cliente.nombre, motivo.descripcion, producto.serial, producto.marca.nombre, producto.modelo")
@Views({
	@View(members="conduce, codigo, fecha; departamento, motivo; cliente; producto"),
	@View(name="NoProducto", members="conduce, codigo, fecha; departamento, motivo; cliente")
})
public class Movimiento extends Identificable {

	@Column(length=6)
	private int conduce;
	
	@Column(length=4)
	private int codigo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("Simple")
	private Cliente cliente;
	
	@Column(length=32)
	private String departamento;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	private Motivo motivo;
	
	@ManyToOne
	@ReferenceView("Simple")
	private Producto producto;

	public int getConduce() {
		return conduce;
	}

	public void setConduce(int conduce) {
		this.conduce = conduce;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
}
