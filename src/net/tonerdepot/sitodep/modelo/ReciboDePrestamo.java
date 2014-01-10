package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Tab(properties="fecha, prestamo.cliente.nombre, prestamo.producto.serial, recibidoPor.nombre")
@View(members="fecha, recibidoPor; prestamo; nota")
public class ReciboDePrestamo extends Identificable {

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@OneToOne
	@ReferenceView("Simple")
	@SearchAction("ReciboDePrestamo.buscarPrestamo")
	private Prestamo prestamo;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Empleado recibidoPor;
	
	@Stereotype("MEMO")
	private String nota;

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
	}
}
