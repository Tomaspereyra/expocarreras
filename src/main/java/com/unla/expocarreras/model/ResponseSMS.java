package com.unla.expocarreras.model;

public class ResponseSMS {
	private String error;
	private String sms_id;
	private String message;
	public ResponseSMS(String error, String sms_id, String message) {
		super();
		this.error = error;
		this.sms_id = sms_id;
		this.message = message;
	}
	
	public ResponseSMS() {
		super();
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSms_id() {
		return sms_id;
	}
	public void setSms_id(String sms_id) {
		this.sms_id = sms_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseSMS [error=" + error + ", sms_id=" + sms_id + ", message=" + message + "]";
	}
	
}
