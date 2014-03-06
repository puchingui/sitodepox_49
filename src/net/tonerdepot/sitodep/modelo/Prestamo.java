package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import net.tonerdepot.sitodep.modelo.Producto.Ubicacion;
import net.tonerdepot.sitodep.validators.*;

import org.hibernate.validator.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Entity
@Tab(properties="conduce, fecha, cliente.nombre, motivo.descripcion, producto.serial, producto.marca.nombre, producto.modelo, recibido")
@Views({
	@View(members="Datos {conduce, fecha, recibido; departamento, motivo; cliente; producto, "
					+ "Otros [bandejaSuperior, bandejaInferior, bandejaADF, toner;"
					+ "cableUSB, cableCorriente, fuente] observaciones} Recibo {reciboDePrestamo}"),
	@View(name="Simple", members="conduce; cliente; producto"),
	@View(name="NoProducto", members="Datos {conduce, fecha, recibido; departamento, motivo; cliente} Recibo {reciboDePrestamo}"),
	@View(name="NoCliente", members="Datos {conduce, fecha, recibido; departamento, motivo; producto} Recibo {reciboDePrestamo}")
})
@EntityValidator(value = ProductoParaPrestamoValidator.class,
	properties = {@PropertyValue(name = "producto")}
)
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
	
	private boolean bandejaSuperior;
	
	private boolean bandejaInferior;
	
	private boolean bandejaADF;
	
	private boolean toner;
	
	private boolean cableUSB;
	
	private boolean cableCorriente;
	
	private boolean fuente;
	
	@Stereotype("MEMO")
	private String observaciones;
	
	@OneToOne(mappedBy="prestamo", cascade=CascadeType.REMOVE)
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

	public boolean isBandejaSuperior() {
		return bandejaSuperior;
	}

	public void setBandejaSuperior(boolean bandejaSuperior) {
		this.bandejaSuperior = bandejaSuperior;
	}

	public boolean isBandejaInferior() {
		return bandejaInferior;
	}

	public void setBandejaInferior(boolean bandejaInferior) {
		this.bandejaInferior = bandejaInferior;
	}

	public boolean isBandejaADF() {
		return bandejaADF;
	}

	public void setBandejaADF(boolean bandejaADF) {
		this.bandejaADF = bandejaADF;
	}

	public boolean isToner() {
		return toner;
	}

	public void setToner(boolean toner) {
		this.toner = toner;
	}

	public boolean isCableUSB() {
		return cableUSB;
	}

	public void setCableUSB(boolean cableUSB) {
		this.cableUSB = cableUSB;
	}

	public boolean isCableCorriente() {
		return cableCorriente;
	}

	public void setCableCorriente(boolean cableCorriente) {
		this.cableCorriente = cableCorriente;
	}

	public boolean isFuente() {
		return fuente;
	}

	public void setFuente(boolean fuente) {
		this.fuente = fuente;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@PrePersist
	public void cambiaUbicacionProducto() throws Exception {
		producto.setPrestado(true);
		producto.setUbicacion(Producto.Ubicacion.Prestado);
	}
	
	@PreRemove
	private void validateOnRemove() {
		if(producto.isPrestado()) {
			producto.setUbicacion(Ubicacion.Taller);
			producto.setPrestado(false);
		}
	}
	
	@AssertTrue(message="No puede prestar un producto vendido")
	private boolean productoNoVendido() {
		return !producto.isVendido();
	}
}
