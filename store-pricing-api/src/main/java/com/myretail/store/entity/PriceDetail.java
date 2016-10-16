package com.myretail.store.entity;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class PriceDetail.
 */
@Document(collection = "prorductPriceDetail")
public class PriceDetail {
	
	/** The id. */
	@Id
	@JsonIgnore
	private String id;
	
	/** The product id. */
	private Long productId;
	
	/** The price. */
	private BigDecimal price;
	
	/** The currency code. */
	private Currency currencyCode;

	/**
	 * Instantiates a new prorduct price detail.
	 */
	public PriceDetail(){
		
	}
	
	/**
	 * Instantiates a new prorduct price detail.
	 *
	 * @param productId the product id
	 * @param price the price
	 * @param currencyCode the currency code
	 */
	public PriceDetail(Long productId, BigDecimal price, Currency currencyCode) {
		super();
		this.productId = productId;
		this.price = price;
		this.currencyCode = currencyCode;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public Currency getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode the new currency code
	 */
	public void setCurrencyCode(Currency currencyCode) {
		this.currencyCode = currencyCode;
	}

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
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
}
