package net.tonerdepot.sitodep.actions;

import org.openxava.actions.*;

/***
 * 230 - Logica estandar para buscar una referencia. Esta accion se ejecuta
 * desde el modulo Prestamo al momento de buscar un Producto, con esto se evita
 * mostrar los productos que ya estan prestados.
 * @author Kenneth Burgos
 *
 */
public class BuscarProductoDesdePrestamoAction extends ReferenceSearchAction {

	public void execute() throws Exception {
		super.execute();
		getTab().setBaseCondition("${ubicacion} <> 'Prestado'");
	}
}
