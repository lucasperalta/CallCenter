package com.lperalta;


import static org.junit.Assert.fail;

import org.junit.Test;
import com.lperalta.dominio.Director;
import com.lperalta.dominio.Empleado;
import com.lperalta.dominio.Llamada;
import com.lperalta.dominio.Operador;
import com.lperalta.dominio.Supervisor;

public class DispatcherTest {

	@Test
	/**
	 * atiendo 10 llamadas al mismo tiempo
	 */
	public void test10LLamadas() {
		System.out.println("Test 10 llamadas");
		Dispatcher dp= new Dispatcher();
		try {
			initialize(dp);
			for (int i = 0; i < 10; i++) {			
				Llamada llamada = new Llamada(i);
				dp.dispatchCall(llamada);
			}		
		} catch (Exception e) {
			fail("Algo salio mal");
		}
		dp.shutDownExecutor();

	}

	@Test
	/**
	 * hago 15 llamadas, atiende 10 al mismo tiempo
	 * las demas quedan en espera a que se liberen empleados
	 */
	public void test15LLamadas() {
		System.out.println("Test 15 llamadas ");

		Dispatcher dp= new Dispatcher();
		try {
			initialize(dp);
			for (int i = 0; i < 15; i++) {			
				Llamada llamada = new Llamada(i);
				dp.dispatchCall(llamada);
			}		
		} catch (Exception e) {
			fail("Algo salio mal");
		}
		dp.shutDownExecutor();


	}
	private void initialize(Dispatcher dp) {
		Empleado e1= new Director(3,"Director");
		Empleado e2= new Director(3	,"Director");
		Empleado e3= new Supervisor(2,"Supervisor");
		Empleado e4= new Operador(2,"Supervisor");
		Empleado e5= new Operador(1,"Operador");
		Empleado e6= new Operador(1,"Operador");
		Empleado e7= new Operador(1,"Operador");
		Empleado e8= new Operador(1,"Operador");
		Empleado e9= new Operador(1,"Operador");
		Empleado e10= new Operador(1,"Operador");
		dp.empleadosDisponibles.offer(e1);
		dp.empleadosDisponibles.offer(e3);
		dp.empleadosDisponibles.offer(e2);
		dp.empleadosDisponibles.offer(e5);
		dp.empleadosDisponibles.offer(e4);
		dp.empleadosDisponibles.offer(e6);
		dp.empleadosDisponibles.offer(e7);
		dp.empleadosDisponibles.offer(e8);
		dp.empleadosDisponibles.offer(e9);
		dp.empleadosDisponibles.offer(e10);
	}
	
	

}
