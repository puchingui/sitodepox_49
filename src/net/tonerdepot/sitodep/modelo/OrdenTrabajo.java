package net.tonerdepot.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Entity
@Tab(properties="ano, codigo, fecha, cliente.nombre, estado.descripcion, prioridad, equipo.serial, equipo.modelo")
	@View(members="ano, codigo, fecha, estado, prioridad;"
			+ "Datos { cliente;"
			+ "Quien [recibido, responsable];"
			+ "Equipo [ equipo;"
						+ "bandejaSuperior, bandejaInferior, toner;"
						+ "cableUSB, cableCorriente, fuente;"
						+ "falta;"
						+ "algoMasQueTrajo;"
						+ "diagnosticoUsuario];"
			+ "observaciones };"
			+ "Cotizaciones { cotizacion1;"
						+ "cotizacion2;"
						+ "cotizacion3 }"
			+ "Solucion { reparador; "
						+ "fechaSolucion; "
						+ "diagnosticoTecnico }")
public class OrdenTrabajo extends Identificable {

	@Column(length=4)
	@DefaultValueCalculator(CurrentYearCalculator.class)
	private int ano;
	
	@Column(length = 6)
	@ReadOnly
	private int codigo;
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	private Date fecha;
	
	@ManyToOne
	@ReferenceView("Simple")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	private Empleado recibido;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	private Empleado responsable;
	
	@ManyToOne
	@ReferenceView("ParaOrden")
	@NoFrame
	private Equipo equipo;
	
	private boolean bandejaSuperior;
	
	private boolean bandejaInferior;
	
	private boolean toner;
	
	private boolean cableUSB;
	
	private boolean cableCorriente;
	
	private boolean fuente;
	
	@Column(length=64)
	private String falta;
	
	@Column(length=64)
	private String algoMasQueTrajo;
	
	@Column(length=64)
	private String diagnosticoUsuario;
	
	@Enumerated(EnumType.STRING)
	private Prioridad prioridad;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@DescriptionsList
	private Estado estado;
	
	@Stereotype("MEMO")
	private String observaciones;
	
	/***
	 * Pestan~a de Cotizaciones
	 */
	@Stereotype("PHOTO")
	private byte[] cotizacion1;
	
	@Stereotype("PHOTO")
	private byte[] cotizacion2;
	
	@Stereotype("PHOTO")
	private byte[] cotizacion3;
	
	/***
	 * Campos para la solucion
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@DescriptionsList
	private Empleado reparador;
	
	private Date fechaSolucion;
	
	@Stereotype("MEMO")
	private String diagnosticoTecnico;
	
	/* 8.20 pag 129 calcula un valor por defecto para multiples usuarios
	 * metodo @PrePersist calculateCodigo()
	 */
	@PrePersist		//ejecutado justo ante de grabar el objeto por primera vez
	public void calculaCodigo() throws Exception {
		Query query = XPersistence.getManager().createQuery(
				"select max(o.codigo) from OrdenTrabajo o where o.ano = :ano");
		query.setParameter("ano", ano);
		Integer ultimoCodigo = (Integer) query.getSingleResult();
		this.codigo = ultimoCodigo == null ? 1 : ultimoCodigo + 1;
	}
	
	private enum Prioridad {
		Indispensable,
		Urgente,
		Normal
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getRecibido() {
		return recibido;
	}

	public void setRecibido(Empleado recibido) {
		this.recibido = recibido;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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

	public String getFalta() {
		return falta;
	}

	public void setFalta(String falta) {
		this.falta = falta;
	}

	public String getAlgoMasQueTrajo() {
		return algoMasQueTrajo;
	}

	public void setAlgoMasQueTrajo(String algoMasQueTrajo) {
		this.algoMasQueTrajo = algoMasQueTrajo;
	}

	public String getDiagnosticoUsuario() {
		return diagnosticoUsuario;
	}

	public void setDiagnosticoUsuario(String diagnosticoUsuario) {
		this.diagnosticoUsuario = diagnosticoUsuario;
	}

	public Prioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public byte[] getCotizacion1() {
		return cotizacion1;
	}

	public void setCotizacion1(byte[] cotizacion1) {
		this.cotizacion1 = cotizacion1;
	}

	public byte[] getCotizacion2() {
		return cotizacion2;
	}

	public void setCotizacion2(byte[] cotizacion2) {
		this.cotizacion2 = cotizacion2;
	}

	public byte[] getCotizacion3() {
		return cotizacion3;
	}

	public void setCotizacion3(byte[] cotizacion3) {
		this.cotizacion3 = cotizacion3;
	}

	public Empleado getReparador() {
		return reparador;
	}

	public void setReparador(Empleado reparador) {
		this.reparador = reparador;
	}

	public Date getFechaSolucion() {
		return fechaSolucion;
	}

	public void setFechaSolucion(Date fechaSolucion) {
		this.fechaSolucion = fechaSolucion;
	}

	public String getDiagnosticoTecnico() {
		return diagnosticoTecnico;
	}

	public void setDiagnosticoTecnico(String diagnosticoTecnico) {
		this.diagnosticoTecnico = diagnosticoTecnico;
	}
}