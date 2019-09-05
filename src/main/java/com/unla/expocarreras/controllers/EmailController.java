package com.unla.expocarreras.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.expocarreras.services.impl.MailServiceImpl;
import com.unla.expocarreras.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/enviar")
public class EmailController {
	
	@Autowired
	@Qualifier("mailService")
	private MailServiceImpl mailService;
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usi;
	
	@GetMapping
	public String enviarEmails() {
		//List<String> emails = new ArrayList<String>();
		//emails.add("ramiropereyra21@gmail.com");
		
		mailService.sendEmail(this.usi.traerEmails(), "probando", "desde java");
		
		return "index";
	}
}
