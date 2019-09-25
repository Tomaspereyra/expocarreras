package com.unla.expocarreras.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Resultado {
	@Id
	private Integer id;
	private int total;
	private double promedio;
	public Resultado(int total,double promedio,Integer id) {
		this.total = total;
		this.promedio = promedio;
		this.id=id;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resultado() {
		super();
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
	@Override
	public String toString() {
		return "Resultado [total=" + total + ", promedio=" + promedio + "]";
	}
	
	
}
