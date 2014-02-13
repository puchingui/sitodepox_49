package net.tonerdepot.sitodep.actions;

import java.util.*;

import javax.ejb.ObjectNotFoundException;

import org.openxava.actions.*;

/***
 * 178 - 10.22 Accion estandar de openxava para buscar es SearchByViewKeyAction
 * @author Kenneth S. Burgos
 *
 */
public class BusquedaExcluyendoVendidoAction extends SearchByViewKeyAction {

	//pregunta si la entidad tiene una propiedad vendido
	private boolean isVendido() {
		return getView().getMetaModel().containsMetaProperty("vendido");
	}
	
	/* coge los valores visualizados en la vista, 
	 * estos valores se usan como clave al buscar
	 */
	protected Map getValuesFromView() throws Exception {
		if(!isVendido()) {		//si no esta vendido, ejecutamos la logica estandar
			return super.getValuesFromView();
		}
		Map values = super.getValuesFromView();
		values.put("vendido", false);	//llenamos la propiedad vendido con false
		return values;
	}
	
	/***
	 * Los miembros a leer de la entidad
	 */
	protected Map getMemberNames() throws Exception {
		if(!isVendido()) {		//si no esta vendido, ejecutamos la logica estandar
			return super.getMemberNames();
		}
		Map members = super.getMemberNames();
		members.put("vendido", null);		//queremos obtener la propiedad vendido, 
		return members;						//aunque no este en la vista
	}
	
	/***
	 * Asignamos los valores desde la entidad a la vista
	 */
	protected void setValuesToView(Map values) throws Exception {
		/***
		 * si tiene una propiedad vendido y vale true lanzamos la misma 
		 * excepcion que openxava lanza cuando el objeto no se encuentra
		 */
		if(isVendido() && (Boolean) values.get("vendido")) {
			throw new ObjectNotFoundException();
		}
		else {
			super.setValuesToView(values); 		//en caso contrario usamos logica estandar
		}
	}
}
