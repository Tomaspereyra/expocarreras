package com.unla.expocarreras.controllers;



import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
	
	 @ExceptionHandler(Exception.class)
	    public String handleError(HttpServletRequest request, Exception e)   {
	     
		 return "redirect:/inicio";
	    }
	 
}
