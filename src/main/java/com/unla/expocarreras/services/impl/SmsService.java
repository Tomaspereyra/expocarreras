package com.unla.expocarreras.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service	
@Qualifier("smsService")
public class SmsService {
	
	
	public String sendSms(String numero) {
		RestTemplate restTemplate = new RestTemplate();
		String token="b8c30b1e2f9cac31b662691faec2ec65";
		String msj ="Hola! ya tenemos los resultados en ...  .Este mensaje fue enviado automaticamente, no lo responda";
		String idDispositivo = "929";
		String resultado = restTemplate.getForObject("https://smsgateway24.com/getdata/addsms?token={token}&sendto={sendto}&body={body}&device_id={device_id}", String.class,token,numero,msj,idDispositivo);
		return resultado;		
		
	}

}
