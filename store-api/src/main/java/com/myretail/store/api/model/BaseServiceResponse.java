/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class BaseServiceResponse is abstract class which extends by all Lookup
 * Service Response. This class contains common attributes and methods to add
 * error details in lookup service response
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public abstract class BaseServiceResponse {

	private boolean isError;
	private List<ErrorDetail> errors = new ArrayList<>();

	/**
	 * Adds the error.
	 *
	 * @param message
	 *            the message
	 */
	public void addError(String message) {
		isError = true;
		ErrorDetail error = new ErrorDetail(message);
		this.errors.add(error);
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<ErrorDetail> getErrors() {
		return errors;
	}

	/**
	 * Gets the checks if is error.
	 *
	 * @return the checks if is error
	 */
	public boolean getIsError() {
		return isError;
	}

}
