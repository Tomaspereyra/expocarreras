package com.unla.expocarreras.services;

import com.unla.expocarreras.error.ReCaptchaInvalidException;

public interface ICaptchaService {
	public String processResponse(final String response) throws ReCaptchaInvalidException;
	
	public String getReCaptchaSite();
	public String getReCaptchaSecret();
}
