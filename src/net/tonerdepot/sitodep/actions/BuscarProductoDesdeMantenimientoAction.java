package net.tonerdepot.sitodep.actions;

import org.openxava.actions.*;

public class BuscarProductoDesdeMantenimientoAction extends ReferenceSearchAction {

	public void execute() throws Exception {
		super.execute();
		getTab().setBaseCondition("${ubicacion} = 'Prestado'");
	}
}
