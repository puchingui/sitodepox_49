package net.tonerdepot.sitodep.actions;

import java.util.*;

import net.tonerdepot.sitodep.modelo.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.validators.*;

/***
 * 182 - Logica para alacenar varios productos 
 * seleccionados en el modo lista hacia el almacen 1.
 * @author Kenneth Burgos
 *
 */
public class AlmacenarEn1Action extends TabBaseAction {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void execute() throws Exception {
		@SuppressWarnings("rawtypes")
		Map valores = new HashMap();	//valores a asignar a cada entidad
		valores.put("ubicacion", Producto.Ubicacion.Almacen1);
		
		for(int row:getSelected()) {
			Map key = (Map) getTab().getTableModel().getObjectAt(row);
			try {
				MapFacade.setValues(getTab().getModelName(), key, valores);
			}
			catch(ValidationException ex) {
				addError("No se pudo almacenar el producto: ", row + 1, key);
				addError(ex.getErrors().toString());
			}
			catch(Exception ex) {
				addError("No se pudo almacenar el producto: ", row + 1, key);
			}
		}
		getTab().deselectAll();
		resetDescriptionsCache();
		
	}

}
