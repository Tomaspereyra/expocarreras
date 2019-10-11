package com.unla.expocarreras.services;



import org.springframework.http.ResponseEntity;

import com.unla.expocarreras.model.ResponseSMS;



public interface ISendSMSService {
	public ResponseEntity<ResponseSMS> sendSms(String numero, String nombre);
}
