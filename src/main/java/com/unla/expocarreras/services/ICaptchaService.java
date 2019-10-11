package com.unla.expocarreras.services;


public interface ICaptchaService {
	public String processResponse(final String response);
	
	public String getReCaptchaSite();
	public String getReCaptchaSecret();
}
