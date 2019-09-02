package com.unla.expocarreras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.expocarreras.model.Usuario;
import com.unla.expocarreras.model.Voto;
import com.unla.expocarreras.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/inicio")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usi;
	
	@GetMapping
	public ModelAndView obtenerForm() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("usuario",new Usuario());
		mav.addObject("voto", new Voto());
		return mav;
	}
	
	@PostMapping("/votar")
	public String ingresarUsuario(Usuario u,Voto v, Model model) {
		u.setVoto(v);
		usi.ingresarUsuario(u);
		return "participantes";
		
	}
	

}
