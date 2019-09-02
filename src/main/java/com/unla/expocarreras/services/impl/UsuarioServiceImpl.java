package com.unla.expocarreras.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.expocarreras.model.Usuario;
import com.unla.expocarreras.repository.IUsuarioRepo;
import com.unla.expocarreras.services.IUsuarioServicio;

@Service("usuarioService")
public class UsuarioServiceImpl implements IUsuarioServicio{
	
	@Autowired
	private IUsuarioRepo usuarioRepo;

	@Override
	public void ingresarUsuario(Usuario usuario) {
		usuario.getVoto().setFecha(new Date());
		this.usuarioRepo.save(usuario);
		
	}

	@Override
	public Optional<Usuario> traerUsuario(Integer id) {
		return this.usuarioRepo.findById(id);
	}
	

}
