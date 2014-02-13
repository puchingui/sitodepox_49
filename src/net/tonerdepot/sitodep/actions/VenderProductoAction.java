package net.tonerdepot.sitodep.actions;

import net.tonerdepot.sitodep.modelo.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

public class VenderProductoAction extends ViewBaseAction {

	public void execute() throws Exception {
		if(getView().getValue("serial") == null) {
			addError("sin_registro");
			return;
		}
		
		Producto producto = XPersistence.getManager()
					.find(Producto.class, getView().getValue("serial"));
		
		producto.setVendido(true);
		addMessage("producto_vendido");
		getView().clear();
	}
	
}
