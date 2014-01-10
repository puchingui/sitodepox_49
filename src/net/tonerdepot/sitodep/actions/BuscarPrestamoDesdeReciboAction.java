package net.tonerdepot.sitodep.actions;

import org.openxava.actions.*;

public class BuscarPrestamoDesdeReciboAction extends ReferenceSearchAction {

	public void execute() throws Exception {
		super.execute();
		getTab().setBaseCondition("${recibido} = false");
	}
}
