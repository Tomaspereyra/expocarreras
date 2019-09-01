package com.unla.expocarreras.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.expocarreras.model.Usuario;
import com.unla.expocarreras.model.Voto;
import com.unla.expocarreras.services.impl.UsuarioServiceImpl;

@Controller
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usi;
	
	@GetMapping("/saludar")
	public String ingresarUsuario() {
		Usuario u = new Usuario("nombre","apellido","email");
		u.setVoto(new Voto(new Date(),50));
		usi.ingresarUsuario(u);
		return "index";
		
	}
	

}
