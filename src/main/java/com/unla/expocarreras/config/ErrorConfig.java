package com.unla.expocarreras.config;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>  {
	

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		// TODO Auto-generated method stub
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/inicio"));
	}
}
