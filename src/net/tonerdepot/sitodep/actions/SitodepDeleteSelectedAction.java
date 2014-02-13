package net.tonerdepot.sitodep.actions;

import java.util.*;

import org.openxava.actions.*;
import org.openxava.model.*;
import org.openxava.model.meta.*;
import org.openxava.validators.*;

//181 - 10.26
public class SitodepDeleteSelectedAction extends TabBaseAction implements IChainAction {

	private String nextAction = null;
	
	private boolean restaurar;
	
	public boolean isRestaurar() {
		return restaurar;
	}

	public void setRestaurar(boolean restaurar) {
		this.restaurar = restaurar;
	}

	/***
	 * CRUD.deleteSelected se ejecutara cuando esta accion finalice
	 */
	public void execute() throws Exception {
		if(!getMetaModel().containsMetaProperty("vendido")) {
			nextAction = "CRUD.deleteSelected";
			
			return;
		}
		//logica para marcar las filas seleccionadas como objetos vendidos
		markSelectedEntitiesAsDeleted();
	}
	
	private MetaModel getMetaModel() {
		return MetaModel.get(getTab().getModelName());
	}
	
	//obligatorio por causa de IChainAction
	public String getNextAction() throws Exception {
		return nextAction;		//si es nulo no se encadena ninguna accion
	}
	
	private void markSelectedEntitiesAsDeleted() throws Exception {
		Map values = new HashMap();
		values.put("vendido", !isRestaurar());
									
		for (int row : getSelected()) {
			Map key = (Map) getTab().getTableModel().getObjectAt(row);
			try {
				MapFacade.setValues(	
						getTab().getModelName(), 
						key, 
						values);
			}
			catch (ValidationException ex) {	
				addError("no_pudo_eliminarse_la_fila", row + 1, key);
				addError(ex.getErrors().toString());
			}
			catch (Exception ex) {		
				addError("no_pudo_eliminarse_la_fila", row + 1, key);
			}
		}
		getTab().deselectAll(); 	//despues de borrar deseleccionamos las filas
		resetDescriptionsCache();	//y reiniciamos el cache de los combos para este usuario
	}

}
