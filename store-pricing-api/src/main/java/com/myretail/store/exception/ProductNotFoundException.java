package com.myretail.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ProductNotFoundException.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lookeup service exception.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	public ProductNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	/**
	 * Instantiates a new lookeup service exception.
	 *
	 * @param msg the msg
	 */
	public ProductNotFoundException(String msg) {
		super(msg);
	}
}
