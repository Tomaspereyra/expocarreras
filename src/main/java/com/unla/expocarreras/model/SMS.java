package com.unla.expocarreras.model;

import java.util.Date;

public class SMS {
	private String token;
	private String email;
	private String pass;
	private String sendTo;
	private String body;
	private Date timetosend;
	private String device_id;
	private String sim;
	
	public SMS() {
		super();
	}

	public SMS(String token, String emailAccount, String password, String sendTo, String body, Date timeToSend,
			String device_id, String sim) {
		super();
		this.token = token;
		this.email = emailAccount;
		this.pass = password;
		this.sendTo = sendTo;
		this.body = body;
		this.timetosend = timeToSend;
		this.device_id = device_id;
		this.sim = sim;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmailAccount() {
		return email;
	}

	public void setEmailAccount(String emailAccount) {
		this.email = emailAccount;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTimeToSend() {
		return timetosend;
	}

	public void setTimeToSend(Date timeToSend) {
		this.timetosend = timeToSend;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}


	
	
	
	
}
