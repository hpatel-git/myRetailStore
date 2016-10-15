/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

/**
 * The Class ProductDetail.
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public class ProductDetail {

	private long productId;
	private String productName;
	private PriceDetail price;

	/**
	 * Instantiates a new product detail.
	 *
	 * @param productBuilder the product builder
	 */
	public ProductDetail(ProductDetailBuilder productBuilder) {
		this.productId = productBuilder.productId;
		this.productName = productBuilder.productName;
		this.price = productBuilder.price;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public PriceDetail getPrice() {
		return price;
	}

	/**
	 * The Class ProductDetailBuilder.
	 */
	public static class ProductDetailBuilder {

		/** The product id. */
		private long productId;
		
		/** The product name. */
		private String productName;
		
		/** The price. */
		private PriceDetail price;

		/**
		 * Instantiates a new product detail builder.
		 *
		 * @param productId the product id
		 * @param productName the product name
		 */
		public ProductDetailBuilder(Long productId, String productName) {
			this.productId = productId;
			this.productName = productName;
		}

		/**
		 * Price.
		 *
		 * @param price the price
		 * @return the product detail builder
		 */
		public ProductDetailBuilder price(PriceDetail price) {
			this.price = price;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the product detail
		 */
		public ProductDetail build() {
			return new ProductDetail(this);
		}

	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(PriceDetail price) {
		this.price = price;
	}
	

}
