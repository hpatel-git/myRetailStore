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

@ControllerAdvice
public class RestExceptionProcessor {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleTypeMismatchException(HttpServletRequest req,
			MethodArgumentTypeMismatchException ex) {
		ResponseBuilder<ErrorDetail> errorResponse = new ApiResponseBody.ResponseBuilder<>();
		errorResponse.addMoreError("Invalid value for field " + ex.getName() + ". Value  '" + ex.getValue() + "' is not allowed");
		return errorResponse.build();
	}
	 
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleBadRequest(HttpServletRequest req,
			BadRequestException ex) {
		return ex.getErrorDetails();
	}
	
	@ExceptionHandler(LookupServiceException.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ApiResponseBody<ErrorDetail> handleLookupServiceException(HttpServletRequest req,
			LookupServiceException ex) {
		ResponseBuilder<ErrorDetail> errorResponse = new ApiResponseBody.ResponseBuilder<>(ex.getMessage());
		return errorResponse.build();
	}
}
