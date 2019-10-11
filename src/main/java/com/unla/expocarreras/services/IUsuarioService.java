package com.unla.expocarreras.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.unla.expocarreras.model.Usuario;

public interface IUsuarioService {
	
	
	public void ingresarUsuario(Usuario u) throws SQLException;
	
	public Optional<Usuario> traerUsuario(Integer id);
	
	public List<Usuario> traerUsuarios();

	

}
