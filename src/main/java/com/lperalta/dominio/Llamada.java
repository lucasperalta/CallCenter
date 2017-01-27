package com.lperalta.dominio;


import java.util.Random;

import com.lperalta.interfaces.Callback;
/**
 * Representa la llamada que se hace al callCenter
 * 
 * @author lucas
 *
 */
public class Llamada implements Runnable {

	//empleado que toma la llamada
	public Empleado empleadoAsignado;
	//metodo callback
	public Callback callBack;
	//numero de llamada
	public int llamadaNumero;
	
	public Llamada(int numeroLlamada) {
		this.llamadaNumero=numeroLlamada;
	}

	@Override
	public void run() {
		 System.out.println("La llamada "+llamadaNumero+" es atendida por un: " + empleadoAsignado.getCategoria());
			try {
				//cada llamada tiene una duracion entre 5 y 10 segundos
			 Random ran = new Random();
			 int x = ran.nextInt(6) + 5;
			Thread.sleep(x*1000);
			} catch (InterruptedException e) {
				System.out.println("LLamada interrumpida");
				
			}
		
		//cuando termino de atender la llamada libero al empleado
		callBack.callBackFunction(empleadoAsignado);
	}



}
