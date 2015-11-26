package com.peafunk.spring4rest.model;

import org.springframework.stereotype.Component;

/**
 * Response object for exceptions for service endpoints.  Returns response Code and Status.
 * @author pellington
 */
@Component
public class Spring4RestServiceExceptionResponse {
	
	public int status;
	public String code;
	
	public static final String SUCCESS = "SUCCESS";
	public static final String DATA = "No Data was found.";
	public static final String FOLDER = "No file folder found for the passed criteria.";
	public static final String SERVICE = "An error occurred processing this request.";
	
	public static final Spring4RestServiceExceptionResponse SUCCESS_RESPONSE = new Spring4RestServiceExceptionResponse(1, SUCCESS);
	public static final Spring4RestServiceExceptionResponse ERROR_DATA_RESPONSE = new Spring4RestServiceExceptionResponse(0, DATA);
	public static final Spring4RestServiceExceptionResponse ERROR_FOLDER_RESPONSE = new Spring4RestServiceExceptionResponse(0, FOLDER);
	public static final Spring4RestServiceExceptionResponse ERROR_SERVICE_RESPONSE = new Spring4RestServiceExceptionResponse(0, SERVICE);
	
	public Spring4RestServiceExceptionResponse(){}
	
	public Spring4RestServiceExceptionResponse (int status, String code) {
		setStatus(status);
		setCode(code);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
