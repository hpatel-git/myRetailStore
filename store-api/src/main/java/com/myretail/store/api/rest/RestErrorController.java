/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.store.api.model.ApiResponseBody;
import com.myretail.store.api.model.ErrorDetail;

/**
 * The Class RestErrorController.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@RestController
public class RestErrorController implements ErrorController {

	/** The Constant DEFAULT_ERR_HTTP_STATUS. */
	private static final HttpStatus DEFAULT_ERR_HTTP_STATUS = HttpStatus.BAD_REQUEST;
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorController.class);

	/**
	 * Error.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the api response body
	 */
	@RequestMapping("/error")
	public ApiResponseBody<ErrorDetail> error(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = parseStatusCode(response.getStatus());
		return new ApiResponseBody.ResponseBuilder<ErrorDetail>(status.name()).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return "/error";
	}

	/**
	 * Parses the status code.
	 *
	 * @param statusCode
	 *            the status code
	 * @return the http status
	 */
	private HttpStatus parseStatusCode(int statusCode) {
		HttpStatus status = DEFAULT_ERR_HTTP_STATUS;
		try {
			status = HttpStatus.valueOf(statusCode);
		} catch (IllegalArgumentException e) {
			LOGGER.error("resp code {} could not cover to http status" + statusCode, e);
		}
		return status;
	}
}
