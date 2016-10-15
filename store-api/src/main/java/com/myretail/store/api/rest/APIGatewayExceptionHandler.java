/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.ApiResponseBody;
import com.myretail.store.api.model.ErrorDetail;

/**
 * The Class APIGatewayExceptionHandler.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@RestControllerAdvice
public class APIGatewayExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle internal server error.
	 *
	 * @param exception
	 *            the exception
	 * @return the api response body
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ LookupServiceException.class })
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleInternalServerError(LookupServiceException exception) {
		return new ApiResponseBody.ResponseBuilder<ErrorDetail>(exception.getMessage()).build();
	}
}