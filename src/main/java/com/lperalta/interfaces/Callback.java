package com.lperalta.interfaces;

import com.lperalta.dominio.Empleado;

/**
 * Interfaz para manejar la llamada de callback desoues de que un thread termina su ejecucion
 * 
 * @author lucas
 *
 */
public interface Callback {

	/**
	 * Metodo que sera llamdado cuadno una llamada se termine
	 * el empleado que atendio la llamada vuleve a estar disponible
	 * @param empleado el empleado que esta disponible para seguir atendiendo llamadas
	 */
	public void callBackFunction(Empleado empleado);
	
}
