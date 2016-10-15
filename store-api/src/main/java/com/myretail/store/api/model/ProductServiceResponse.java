/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.model;

/**
 * The Class ProductServiceResponse.
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
public class ProductServiceResponse extends BaseServiceResponse{

	private long productId;
	private String productName;
	
	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public long getProductId() {
		return productId;
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
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductServiceResponse [productId=" + productId + ", productName=" + productName + ", getProductId()="
				+ getProductId() + ", getProductName()=" + getProductName() + ", getErrors()=" + getErrors()
				+ ", getIsError()=" + getIsError() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	 
	
}
