package com.unla.expocarreras.controllers.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SMSController {
	
	@GetMapping(value="send")
	public String sendSms() {
		RestTemplate restTemplate = new RestTemplate();
		String token="b8c30b1e2f9cac31b662691faec2ec65";
		String numero = "1130840174";
		String msj ="hola";
		String idDispositivo = "929";
		String resultado = restTemplate.getForObject("https://smsgateway24.com/getdata/addsms?token={token}&sendto={sendto}&body={body}&device_id={device_id}", String.class,token,numero,msj,idDispositivo);
		System.out.println(resultado);
		return resultado;
		
		

		
		
	}

}
