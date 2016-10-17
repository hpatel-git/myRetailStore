/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.model;

/**
 * The Class PriceServiceResponse.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public class PriceServiceErrorRes{

	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "PriceServiceErrorRes [timestamp=" + timestamp + ", status=" + status + ", error=" + error
				+ ", exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}

}
