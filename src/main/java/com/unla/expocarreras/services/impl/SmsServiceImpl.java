package com.unla.expocarreras.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.unla.expocarreras.model.ResponseSMS;
import com.unla.expocarreras.services.ISendSMSService;
@Service	
@Qualifier("smsService")
public class SmsServiceImpl  implements ISendSMSService{
	

	
	public ResponseEntity<ResponseSMS> sendSms(String numero, String nombre) {
		RestTemplate restTemplate = new RestTemplate();
		String token="b8c30b1e2f9cac31b662691faec2ec65";
		String msj ="Hola "+nombre+ "! ya tenemos los resultados en http://sg.unla.edu.ar/expocarreras/resultados  .Este mensaje fue enviado automaticamente, no lo responda";
		String idDispositivo = "929";
		 
		Map<String,String> body = new HashMap<>();
		body.put("token", token);
		body.put("sendto", numero);
		body.put("body", msj);
		body.put("device_id", idDispositivo);		
		 
		ResponseEntity<ResponseSMS> resultado = restTemplate.postForEntity("https://smsgateway24.com/getdata/addsms?token={token}&sendto={sendto}&body={body}&device_id={device_id}",body, ResponseSMS.class,body);
		
		return resultado;		
		
	}


}
