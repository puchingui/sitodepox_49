package net.tonerdepot.sitodep.ordentrabajo.modelo;

import javax.persistence.*;

import net.tonerdepot.sitodep.jpa.*;

import org.openxava.annotations.*;

@Entity
@Tab(properties="nombre, rol.descripcion")
public class Empleado extends Identificable {

	@Required
	@Column(length=50)
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@DescriptionsList
	private Rol rol;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}