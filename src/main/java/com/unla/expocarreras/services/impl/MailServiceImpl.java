package com.unla.expocarreras.services.impl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.unla.expocarreras.services.ISendMails;

@Service("mailService")
public class MailServiceImpl implements ISendMails {

	@Override
	public void sendEmail(String[] emails, String subject, String text) {
		JavaMailSenderImpl mailSender = this.getJavaMailSender();
		
		SimpleMailMessage message = new SimpleMailMessage();		
	
		message.setBcc(emails); // Bcc envia los mails pero ocultando la lista de destinatarios
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

	}
	

	
	private JavaMailSenderImpl getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("carreras.expo@gmail.com");
		mailSender.setPassword("rauflgcuplzatmfa");
		
		Properties props = mailSender.getJavaMailProperties();
		
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.debug", "true");
		
		return mailSender;
	}

}
