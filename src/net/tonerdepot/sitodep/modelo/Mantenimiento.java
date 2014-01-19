package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Tab(properties="fecha, tecnico.nombre, producto.serial, reporte")
@Views({
	@View(members="fecha, tecnico; producto; reporte"),
	@View(name="NoProducto", members="fecha, tecnico; reporte")
})
public class Mantenimiento extends Identificable {

	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@ManyToOne
	@DescriptionsList
	private Empleado tecnico;
	
	@ManyToOne
	@ReferenceView("Simple")
	@SearchAction("Mantenimiento.buscarProducto")
	private Producto producto;
	
	@Stereotype("MEMO")
	private String reporte;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Empleado getTecnico() {
		return tecnico;
	}

	public void setTecnico(Empleado tecnico) {
		this.tecnico = tecnico;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
	@AssertTrue(message="El producto elegido no requiere mantenimiento")
	private boolean productoValidator() {
		return producto.isPrestado();
	}
}
