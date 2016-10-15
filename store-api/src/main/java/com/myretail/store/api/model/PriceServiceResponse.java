/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;
/**
 * The Class PriceServiceResponse.
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public class PriceServiceResponse extends BaseServiceResponse{

	/** The value. */
	private double value;
	
	/** The currency code. */
	private String currencyCode;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode the new currency code
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PriceServiceResponse [value=" + value + ", currencyCode=" + currencyCode + ", getValue()=" + getValue()
				+ ", getCurrencyCode()=" + getCurrencyCode() + ", getErrors()=" + getErrors() + ", getIsError()="
				+ getIsError() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
