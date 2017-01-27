package com.lperalta.dominio;

public class Empleado  {

	//prioridad en atender llamada (el valor mas bajo es mas prioritario)
	private int prioridad;
	//categoria operador, supervisor, director
	private String categoria;
	
	
	
	public Empleado(int prioridad,String categoria) {
		this.prioridad = prioridad;
		this.categoria=categoria;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}





}
