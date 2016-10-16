/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

import java.math.BigDecimal;

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
	private BigDecimal price;

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
	public PriceDetail(BigDecimal price, String currencyCode) {
		this.price = price;
		this.currencyCode = currencyCode;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public BigDecimal getPrice() {
		return price;
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
		return "PriceDetail [value=" + price + ", currencyCode=" + currencyCode + "]";
	}

}
