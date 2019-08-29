package com.unla.expocarreras.model;

public class Usuario {
private int id;
private String nombre;
private String apellido;
private String email;
private Voto voto;

public Usuario() {
}

public Usuario(String nombre, String apellido, String email) {
	super();
	this.nombre = nombre;
	this.apellido = apellido;
	this.email = email;
}

public Voto getVoto() {
	return voto;
}

public void setVoto(Voto voto) {
	this.voto = voto;
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

@Override
public String toString() {
	return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
}


}
