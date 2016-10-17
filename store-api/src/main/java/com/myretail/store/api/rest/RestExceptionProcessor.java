package com.myretail.store.api.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.myretail.store.api.exception.BadRequestException;
import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.ApiResponseBody;
import com.myretail.store.api.model.ErrorDetail;
import com.myretail.store.api.model.ApiResponseBody.ResponseBuilder;

/**
 * The Class RestExceptionProcessor. This class use to covert all exception to custom Json Body. 
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@ControllerAdvice
public class RestExceptionProcessor {

	/**
	 * Handle type mismatch exception.
	 *
	 * @param req the req
	 * @param ex the ex
	 * @return the api response body
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleTypeMismatchException(HttpServletRequest req,
			MethodArgumentTypeMismatchException ex) {
		ResponseBuilder<ErrorDetail> errorResponse = new ApiResponseBody.ResponseBuilder<>();
		errorResponse.addMoreError(
				"Invalid value for field " + ex.getName() + ". Value  '" + ex.getValue() + "' is not allowed");
		return errorResponse.build();
	}

	/**
	 * Handle bad request.
	 *
	 * @param req the req
	 * @param ex the ex
	 * @return the api response body
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleBadRequest(HttpServletRequest req, BadRequestException ex) {
		return ex.getErrorDetails();
	}

	/**
	 * Handle lookup service exception.
	 *
	 * @param req the req
	 * @param ex the ex
	 * @return the api response body
	 */
	@ExceptionHandler(LookupServiceException.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleLookupServiceException(HttpServletRequest req,
			LookupServiceException ex) {
		ResponseBuilder<ErrorDetail> errorResponse = new ApiResponseBody.ResponseBuilder<>(ex.getMessage());
		return errorResponse.build();
	}
}
