package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Entity
@Tab(properties="codigo, fecha, prestamo.cliente.nombre, prestamo.producto.serial, recibidoPor.nombre")
@View(members="codigo, fecha, recibidoPor; prestamo; nota")
public class ReciboDePrestamo extends Identificable {

	@SearchKey
	@Column(length = 6)
	@ReadOnly
	private int codigo;
	
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@OneToOne
	@ReferenceView("Simple")
	@SearchAction("ReciboDePrestamo.buscarPrestamo")
	private Prestamo prestamo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	@NoCreate
	@NoModify
	private Empleado recibidoPor;
	
	@Stereotype("MEMO")
	private String nota;
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Empleado getRecibidoPor() {
		return recibidoPor;
	}

	public void setRecibidoPor(Empleado recibidoPor) {
		this.recibidoPor = recibidoPor;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	@PrePersist
	public void cambiaUbicacionProducto() throws Exception {
		prestamo.setRecibido(true);
		prestamo.getProducto().setUbicacion(Producto.Ubicacion.Taller);
		prestamo.getProducto().setPrestado(false);
		
		Query query = XPersistence.getManager().createQuery(
				"select max(codigo) from " + getClass().getName());
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}
}
