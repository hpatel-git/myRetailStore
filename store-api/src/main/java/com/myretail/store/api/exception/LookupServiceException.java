/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class LookupServiceException is used to bubble up exception details from Lookup Service to Resource.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since   10/15/2016
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LookupServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lookeup service exception.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	public LookupServiceException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * Instantiates a new lookeup service exception.
	 *
	 * @param msg the msg
	 */
	public LookupServiceException(String msg) {
		super(msg);
	}
}