package net.tonerdepot.sitodep.validators;

import net.tonerdepot.sitodep.modelo.*;

import org.openxava.util.*;
import org.openxava.validators.*;

@SuppressWarnings("serial")
public class ProductoParaPrestamoValidator implements IValidator {

	private Producto producto;
	
	public void validate(Messages errors) throws Exception {
		if(producto.isPrestado() == true) {
			errors.add("producto_prestado", producto.getSerial());
		}
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
