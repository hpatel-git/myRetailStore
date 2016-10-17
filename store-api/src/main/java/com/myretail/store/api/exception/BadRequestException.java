/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myretail.store.api.model.ApiResponseBody;
import com.myretail.store.api.model.ErrorDetail;

/**
 * The Class LookupServiceException is used to bubble up exception details from Lookup Service to Resource.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since   10/15/2016
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ApiResponseBody<ErrorDetail> errorDetails;
	 
	 
	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param msg the msg
	 * @param errorDetails the error details
	 */
	public BadRequestException(String msg, ApiResponseBody<ErrorDetail> errorDetails) {
		super(msg);
		this.errorDetails = errorDetails;
	}

	/**
	 * Instantiates a new bad request exception.
	 *
	 * @param msg the msg
	 */
	public BadRequestException(String msg) {
		super(msg);
		this.errorDetails = new ApiResponseBody.ResponseBuilder<ErrorDetail>(msg).build();
	}
	
	/**
	 * Gets the error details.
	 *
	 * @return the error details
	 */
	public ApiResponseBody<ErrorDetail> getErrorDetails() {
		return errorDetails;
	}
	
}