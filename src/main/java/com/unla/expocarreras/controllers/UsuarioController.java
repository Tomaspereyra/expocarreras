package com.unla.expocarreras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.expocarreras.model.Resultado;
import com.unla.expocarreras.model.Usuario;
import com.unla.expocarreras.model.Voto;
import com.unla.expocarreras.reCaptcha.CaptchaSettings;
import com.unla.expocarreras.repository.IResultadoRepo;
import com.unla.expocarreras.services.impl.CaptchaService;
import com.unla.expocarreras.services.impl.MailServiceImpl;
import com.unla.expocarreras.services.impl.SmsService;
import com.unla.expocarreras.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/inicio")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usi;
	
	@Autowired
	@Qualifier("mailService")
	private MailServiceImpl mailService;
	
	@Autowired
	@Qualifier("smsService")
	private SmsService smsService;
	@Autowired
	private IResultadoRepo resultadoRepository; 
	
	@Autowired
	@Qualifier("captchaService")
	private CaptchaService captchaService;
	@GetMapping
	public ModelAndView obtenerForm() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("usuario",new Usuario());
		mav.addObject("voto", new Voto());
		mav.addObject("captchaSettings", new CaptchaSettings());
		
		return mav;
	}
	
	@SuppressWarnings("finally")
	@PostMapping("/votar")
	public ModelAndView ingresarUsuario(Usuario u, Voto v,
			@RequestParam(name = "g-recaptcha-response") String response) {
		ModelAndView mv = null;
		String error = captchaService.processResponse(response);

		if (!StringUtils.isEmpty(error)) { // si hay algun error
			
			mv.addObject("errorCaptcha", "No se pudo verificar el captcha:"+error);

		}

		else {

			u.setVoto(v);

			try {
				usi.ingresarUsuario(u);
				mv = new ModelAndView("participantes");

			} catch (Exception e) {
				mv = new ModelAndView("index");
				if (e instanceof DataIntegrityViolationException) {
					mv.addObject("error", "El email que ingreso ya registro un voto");
				}

			}
		}
		return mv;

	}
	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("resultado",new Resultado());
		
		return mav;
	}
	
	@PostMapping("/enviar")
	public ModelAndView enviarResultados(Resultado resultado, Model model) {
		resultado.setPromedio(usi.calcularPromedio());
		resultado.setId(1);
		resultadoRepository.deleteAll();
		resultadoRepository.save(resultado);
		mailService.sendEmail(usi.traerEmails(), "Expo carreras - Lic. Sistemas", "Hola! Ya tenemos los resultados en ...   .Este email se ha enviado automaticamente, no responder");
		
		for(Usuario u: this.usi.traerUsuarios()) {
			this.smsService.sendSms(Integer.toString(u.getNumero()));
		}
		
		ModelAndView mav = new ModelAndView("resultados");
		mav.addObject("resultado", resultado);
		
		return mav;
		
		
		
	}
	@GetMapping("/resultados")
	public ModelAndView mostrarResultados() {	
		
		ModelAndView mav = new ModelAndView("resultados");
		mav.addObject("resultado",resultadoRepository.findById(1).get());
		
		return mav;
		
		
		
	}
	
	
	

}
