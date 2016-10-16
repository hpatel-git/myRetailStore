/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * The Class PriceServiceResponse.
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public class PriceUpdateRequest{

	private Long productId;
	private BigDecimal price;
	private String currencyCode;
	

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
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
		return "PriceServiceResponse [productId=" + productId + ", price=" + price + ", currencyCode=" + currencyCode
				+ "]";
	}
	

}
