package com.unla.expocarreras.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.unla.expocarreras.reCaptcha.CaptchaSettings;
import com.unla.expocarreras.reCaptcha.RecaptchaError;
import com.unla.expocarreras.services.ICaptchaService;


@Service("captchaService")
public class CaptchaService implements ICaptchaService {
	
	 @Autowired
	 private HttpServletRequest request;
	@Autowired
	private CaptchaSettings captchaSettings;
	
	private static final String GOOGLE_VERIFY_URL ="https://www.google.com/recaptcha/api/siteverify";

	
	private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
	
	@Override
	public String processResponse(String response) {
		 RestTemplate restTemplate = new RestTemplate();

	    Map<String, String> body = new HashMap<>();

	    body.put("secret", getReCaptchaSecret());

	    body.put("response", response);

	    body.put("remoteip", this.getClientIP());

	   

	    ResponseEntity<Map> recaptchaResponseEntity = 
	    		restTemplate.postForEntity(GOOGLE_VERIFY_URL+
	    		          "?secret={secret}&response={response}&remoteip={remoteip}",body,Map.class, body);
	  
	             
	    Map<String, Object> responseBody =  recaptchaResponseEntity.getBody();       
	    System.out.println(responseBody);
	    boolean recaptchaSucess = (Boolean)responseBody.get("success");

	    if ( !recaptchaSucess) {

	        List<String> errorCodes = (List<String>)responseBody.get("error-codes");

	         

	        String errorMessage = errorCodes.stream().map(s -> RecaptchaError.RECAPTCHA_ERROR_CODE.get(s))
	        		.collect(Collectors.joining(", "));             

	        return errorMessage;

	      }else {

	        return ""; //si no hay errores

	      }
	    }
	

	 

	    private String getClientIP() {
	        final String xfHeader = request.getHeader("X-Forwarded-For");
	        if (xfHeader == null) {
	            return request.getRemoteAddr();
	        }
	        return xfHeader.split(",")[0];
	    }



		@Override
		public String getReCaptchaSite() {
			return this.captchaSettings.getSite();
		}



		@Override
		public String getReCaptchaSecret() {
			return this.captchaSettings.getSecret();
		}

}
