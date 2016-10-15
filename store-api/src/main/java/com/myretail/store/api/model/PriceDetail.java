/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

/**
 * The Class PriceDetail.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 * 
 */
public class PriceDetail {

	/** The value. */
	private double value;

	/** The currency code. */
	private String currencyCode;

	/**
	 * Instantiates a new price detail.
	 *
	 * @param value
	 *            the value
	 * @param currencyCode
	 *            the currency code
	 */
	public PriceDetail(double value, String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/*
	 * Overrides default toString() method to log object details
	 */
	@Override
	public String toString() {
		return "PriceDetail [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
