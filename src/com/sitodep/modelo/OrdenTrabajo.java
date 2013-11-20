package com.sitodep.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

@Entity
@Tab(properties="codigo, fecha, cliente.nombre, recibido.nombre, listo, nota")
@View(members="ano, codigo, fecha, listo; cliente; Quien [recibido, responsable]; detalles; nota")
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
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@ReferenceView("Simple")
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	private Empleado recibido;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@DescriptionsList
	private Empleado responsable;
	
	private boolean listo;
	
	@OneToMany(mappedBy = "padre", cascade = CascadeType.ALL)
	@ListProperties("equipo.serial, equipo.tipo.descripcion, equipo.marca.nombre, equipo.modelo, estado.descripcion, equipo.observacion")
	private Collection<Detalle> detalles = new ArrayList<Detalle>();
	
	@Stereotype("MEMO")
	private String nota;
	
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

	public boolean isListo() {
		return listo;
	}

	public void setListo(boolean listo) {
		this.listo = listo;
	}

	public Collection<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Collection<Detalle> detalles) {
		this.detalles = detalles;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}