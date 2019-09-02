package com.unla.expocarreras.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voto {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private int id;
private Date fecha;
private int estimacion;
public Voto() {
	
}
public Voto(int estimacion) {
	
	this.fecha = new Date();
	this.estimacion = estimacion;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public int getEstimacion() {
	return estimacion;
}
public void setEstimacion(int estimacion) {
	this.estimacion = estimacion;
}

}
