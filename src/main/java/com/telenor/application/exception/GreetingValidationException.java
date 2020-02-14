package com.telenor.application.exception;

public class GreetingValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	private String errorMessage;



	public GreetingValidationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}



	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
