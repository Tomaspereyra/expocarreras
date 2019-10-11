package com.unla.expocarreras.controllers;



import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.expocarreras.model.ResponseSMS;
import com.unla.expocarreras.model.Resultado;
import com.unla.expocarreras.model.Usuario;
import com.unla.expocarreras.model.Voto;
import com.unla.expocarreras.reCaptcha.CaptchaSettings;
import com.unla.expocarreras.repository.IResultadoRepo;
import com.unla.expocarreras.services.impl.CaptchaServiceImpl;
import com.unla.expocarreras.services.impl.MailServiceImpl;
import com.unla.expocarreras.services.impl.SmsServiceImpl;
import com.unla.expocarreras.services.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("inicio")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioServiceImpl usi;
	
	@Autowired
	@Qualifier("mailService")
	private MailServiceImpl mailService;
	
	@Autowired
	@Qualifier("smsService")
	private SmsServiceImpl smsService;
	@Autowired
	private IResultadoRepo resultadoRepository; 
	
	@Autowired
	@Qualifier("captchaService")
	private CaptchaServiceImpl captchaService;
	
	@GetMapping
	public ModelAndView obtenerForm() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("usuario",new Usuario());
		mav.addObject("voto", new Voto());
		mav.addObject("captchaSettings", new CaptchaSettings());
		
		return mav;
	}
	
	@PostMapping("/votar")
	public ModelAndView ingresarUsuario(Usuario u, Voto v,
			@RequestParam(name = "g-recaptcha-response") String response) {
		ModelAndView mv = null;
		String error = captchaService.processResponse(response);

		if (!StringUtils.isEmpty(error)) { // si hay algun error
			
			mv = new ModelAndView("/index");
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
	//Envio Emails con la url de los resultados
	@GetMapping("/enviaremail")
	public ModelAndView enviarEmails() {
		boolean emailsEnviados= true;
		ModelAndView mav = new ModelAndView("admin");
		
	
		try {
			Resultado resultado = resultadoRepository.getOne(1);
			if(resultado.getTotal()!=0) {
				mailService.sendEmail(usi.traerEmails(), "SabiduriaTodxs - Lic. Sistemas", "Hola!\nYa tenemos los resultados en http://sg.unla.edu.ar/expocarreras/resultados . \nEste email se ha enviado automaticamente, no responder.");
				mav.addObject("emailsEnviados",emailsEnviados);
			}
			
		}
		catch(Exception e) {
			emailsEnviados=false;
			mav.addObject("errorMail",e.getMessage());
			
			if(e instanceof EntityNotFoundException ) {
				mav.addObject("resultadoNulo", "Debe cargar el resultado primero");
			}
			

			
		}
		
		return mav;
		
		
	}

	// Envio SMS con la url de los resultados
	@GetMapping("/enviarsms")
	public ModelAndView enviarSMS() {
		ModelAndView mav = new ModelAndView("admin");
		Integer smsEnviados = 0;
		Integer smsNoEnviados = 0;
		try {
			Resultado resultado = resultadoRepository.getOne(1);
			
			if(resultado!=null) {
				System.out.println(resultado);
			for (Usuario u : this.usi.traerUsuarios()) {
				ResponseEntity<ResponseSMS> res = this.smsService.sendSms(Integer.toString(u.getNumero()),
						u.getNombre());
				if (res.getBody().getError().compareTo("0") == 0) {
					smsEnviados += 1;
				} else {
					smsNoEnviados += 1;

				}

			}
			mav.addObject("enviados", smsEnviados);
			mav.addObject("noEnviados", smsNoEnviados);
		}
			}

		catch (EntityNotFoundException e) {
			mav.addObject("resultadoNulo", "Debe cargar el resultado primero");

		}

		return mav;
	}
	
	@PostMapping("/cargar")
	public ModelAndView enviarResultados(Resultado resultado, Model model) {

		ModelAndView mav = new ModelAndView("admin");

		resultado.setPromedio(usi.calcularPromedio());

		resultado.setId(1);
		try {
			resultadoRepository.deleteAll();
			resultadoRepository.save(resultado);
			mav.addObject("cargado","Resultado cargado");
		}
		catch(Exception e) {
			mav.addObject("errorResultado","No se pudo guardar el resultado"+e.getMessage());
			
		}

		return mav;

	}
	/*@GetMapping("/resultados")
	public ModelAndView mostrarResultados() {	
		
		ModelAndView mav = new ModelAndView("resultados");
		mav.addObject("resultado",resultadoRepository.findById(1).get());
		
		return mav;	
		
		
	}*/

	
	
	

}
