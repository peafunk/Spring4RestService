package com.peafunk.spring4rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.peafunk.spring4rest.model.Spring4RestServiceExceptionResponse;



@ControllerAdvice
public class Spring4RestServiceExceptionHandler {

	/**
	 * Error occurred looking up bucket mapping
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Spring4RestServiceDataException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Spring4RestServiceExceptionResponse handleDataException(Spring4RestServiceDataException ex) {
		return Spring4RestServiceExceptionResponse.ERROR_DATA_RESPONSE;
		
	}

	/**
	 * No fragment found on file system
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Spring4RestServiceFolderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Spring4RestServiceExceptionResponse handleFileFolderException(Spring4RestServiceFolderNotFoundException ex) {
		return Spring4RestServiceExceptionResponse.ERROR_FOLDER_RESPONSE;
		
	}	

	/**
	 * Catch all exception handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Spring4RestServiceExceptionResponse handleAllException(Exception ex) {
		return Spring4RestServiceExceptionResponse.ERROR_SERVICE_RESPONSE;
	}
	
}
