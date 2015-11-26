package com.peafunk.spring4rest.exception;

@SuppressWarnings("serial")
public class Spring4RestServiceDataException extends Exception {

	protected String message;
	protected String description;
	protected Exception exception;
	
	public Spring4RestServiceDataException(){}
	
	public Spring4RestServiceDataException(String message){
		this.message = message;
	}
	public Spring4RestServiceDataException(String message, String description){
		this.message = message;
		this.description = description;
	}
	public Spring4RestServiceDataException(String message, Exception e){
		this.message = message;
		this.exception = e;
	}
	public Spring4RestServiceDataException(String message, String description, Exception e){
		this.message = message;
		this.description = description;
		this.exception = e;
	}
	
}
