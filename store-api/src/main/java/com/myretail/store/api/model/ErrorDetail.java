/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

/**
 * The Class ErrorDetail represent error details. API response body represent error using this class. 
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 * 
 */
public class ErrorDetail {

	private String message;

	/**
	 * Instantiates a new error detail.
	 *
	 * @param message
	 *            the message
	 */
	public ErrorDetail(String message) {
		this.message = message;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * Overrides default toString() method to log object details  
	 */
	@Override
	public String toString() {
		return "ErrorDetail [message=" + message + "]";
	}

}
