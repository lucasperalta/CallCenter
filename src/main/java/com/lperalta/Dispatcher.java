package com.lperalta;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.lperalta.dominio.Empleado;
import com.lperalta.dominio.Llamada;
import com.lperalta.interfaces.Callback;

/**
 * Clase encargada de manejar las llamadas entrantes
 * @author lucas
 *
 */

public class Dispatcher implements Callback {
	/**
	 * Los empleados disponibles para atender llamadas son puestos en una cola bloqueante 
	 * de prioridad de esta manera se soluciona el problema de tener que decidir que tipo de empleado
	 * atiende primero las llamadas, siempre el empleado con prioridad mas baja va a estar primero en la 
	 * cola para atender llamadas
	 */
	public PriorityBlockingQueue<Empleado> empleadosDisponibles ;
	private static final int cantidadLLamadas = 10;
	private ExecutorService  executor;
	
	
	public Dispatcher() {

		//Comparator para la cola de prioridad asi los ordena en la cola segun la prioridad mas baja
		Comparator<Empleado> compEmpleadoPrioridad = new Comparator<Empleado>() {

			@Override
			public int compare(Empleado o1, Empleado o2) {
				if(o1.getPrioridad() > o2.getPrioridad()){
					return 1;
				} 
				else if(o1.getPrioridad() < o2.getPrioridad()){
					return -1;
				}
				else return 0;
			}
		};
		
		empleadosDisponibles= new PriorityBlockingQueue<>(10,compEmpleadoPrioridad );
		/**
		 * Creamos un pool de threads con una cantidad fija de threads que podamos ejecutar (llamadas que se pueden atender al mismo tiempo)
		 * Cuando se llega al maximo de threads creados y llega una nueva llamada queda en una cola de espera hasta que un thread se libere
		 * y se pueda usar
		 */
		executor= Executors.newFixedThreadPool(cantidadLLamadas);
	} 

	/**
	 * Metodo encargado de despachar las llamadas entrantes
	 * 
	 * @param llamada
	 * @throws InterruptedException
	 */
	public void dispatchCall(Llamada llamada) throws InterruptedException{
		/**
		 * Toma un empleado disponible de la cola (el de prioridad mas baja siempre esta primero)
		 * si no hay empleados libres el metodo take espera hasta que haya uno disponible para continuar
		 * de esta manera la llamada queda esperando que haya un empleado libre para asignarsela
		 */
	
		llamada.empleadoAsignado=empleadosDisponibles.take();
		//a la llamada asiganmos un callback
		llamada.callBack=this;
		//lanzamos el thread
		executor.submit(llamada);

	}

/**
 * metodo que se va a llamar cuando la llamada termine 
 * vuelve a poner en la cola al empleado que se libero
 */
	@Override
	public void callBackFunction(Empleado empleado) {
		System.out.println("El "+empleado.getCategoria()+" esta disponible");
		empleadosDisponibles.offer(empleado);

	}
	
	
	/**
	 * Intenta terminar el executor service 
	 * una vez que se terminaron las llamadas a atender
	 * 
	 */
	public void shutDownExecutor(){
		executor.shutdown();
		try {
			if (executor.awaitTermination(12, TimeUnit.SECONDS)){
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
		
		}

	}
	

}
