package com.unla.expocarreras.model;

import java.util.Date;

public class Voto {
private int id;
private Date fecha;
private int estimacion;
public Voto() {
	super();
}
public Voto(int id, Date fecha, int estimacion) {
	super();
	this.id = id;
	this.fecha = fecha;
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
