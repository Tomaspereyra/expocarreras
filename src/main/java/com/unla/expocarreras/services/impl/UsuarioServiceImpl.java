package com.unla.expocarreras.services.impl;

import java.util.Date;
import java.util.List;
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

	@Override
	public List<Usuario> traerUsuarios() {
		return this.usuarioRepo.findAll();
	}
	
	public String[] traerEmails(){
		List<Usuario> usuarios = this.traerUsuarios();
		String[] emails = new String[usuarios.size()];
		
		for(int i=0;i<usuarios.size();i++) {
			emails[i] = usuarios.get(i).getEmail();
		}
		
		return emails;
	}
	
	}
	
	


