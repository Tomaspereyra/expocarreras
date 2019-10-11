package com.unla.expocarreras.controllers;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.expocarreras.repository.IResultadoRepo;

@Controller
@RequestMapping("resultados")
public class ResultadoController {
	@Autowired
	private IResultadoRepo resultadoRepository; 
	
	@GetMapping
	public ModelAndView mostrarResultados() {	
		
		ModelAndView mav = new ModelAndView("resultados");
		try {
		mav.addObject("resultado",resultadoRepository.findById(1).get());
		}catch(Exception e ) {
			mav.addObject("error","Los resultados no se han cargado aun");
			
		}
		
		return mav;	
		
		
	}
}
