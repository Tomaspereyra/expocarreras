package com.unla.expocarreras.services;

import java.util.List;
import java.util.Optional;

import com.unla.expocarreras.model.Usuario;

public interface IUsuarioServicio {
	
	
	public void ingresarUsuario(Usuario u);
	
	public Optional<Usuario> traerUsuario(Integer id);
	
	public List<Usuario> traerUsuarios();

	

}
