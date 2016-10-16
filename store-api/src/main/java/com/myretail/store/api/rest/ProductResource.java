/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.rest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.ApiResponseBody;
import com.myretail.store.api.model.ApiResponseBody.ResponseBuilder;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.PriceDetail;
import com.myretail.store.api.model.PriceServiceResponse;
import com.myretail.store.api.model.ProductDetail;
import com.myretail.store.api.model.ProductServiceResponse;
import com.myretail.store.api.service.PriceLookupService;
import com.myretail.store.api.service.ProductLookupService;

/**
 * ProductResource.
 *
 * @author Hardikkumar Patel
 */
@RestController
@RequestMapping("/v1/products")
public class ProductResource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
	@Autowired
	private PriceLookupService priceLookupService;
	@Autowired
	private ProductLookupService productLookupService;

	/**
	 * This method list all categories present into database. This method is
	 * also used to find category by category id and device type
	 *
	 * @param productId the product id
	 * @return the categories
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiResponseBody<ProductDetail> retrieveProductDtlsById(@PathVariable("id") Long productId) {
		LOGGER.debug("ProductResource.retrieveProductDtlsById : Retrieving product details by Product ID " + productId);
		Future<BaseServiceResponse> pricingServiceResponse = priceLookupService.findProductDetail(productId);
		Future<BaseServiceResponse> productServiceResponse = productLookupService.findProductDetail(productId);
		waitForTasksToComplete(pricingServiceResponse,productServiceResponse);
		ProductServiceResponse productDetailRes = null;
		PriceServiceResponse priceDetailsRes = null;
		try {
			if (productServiceResponse.get().getIsError()) {
				return handleProductServiceError(productServiceResponse);
			}
			if (pricingServiceResponse.get().getIsError()) {
				return handleProductServiceError(pricingServiceResponse);
			}
			priceDetailsRes = (PriceServiceResponse) pricingServiceResponse.get();
			productDetailRes = (ProductServiceResponse) productServiceResponse.get();

		} catch(ExecutionException | InterruptedException e){
			LOGGER.error("Error while looking up product details from ProductLookupService ", e);
			throw new LookupServiceException(e.getCause().getMessage());
		} 
		ProductDetail details = new ProductDetail.ProductDetailBuilder(productDetailRes.getProductId(),
				productDetailRes.getProductName()).price(new PriceDetail(priceDetailsRes.getPrice(), priceDetailsRes.getCurrencyCode().getSymbol())).build();
		return new ApiResponseBody.ResponseBuilder<ProductDetail>(details).build();
	}

	/**
	 * Update product price.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public void updateProductPrice() {

	}

	/**
	 * Wait for tasks to complete.
	 *
	 * @param priceResponse the price response
	 * @param productResponse the product response
	 */
	private void waitForTasksToComplete(Future<BaseServiceResponse> priceResponse ,Future<BaseServiceResponse> productResponse){
		while (!(priceResponse.isDone() && productResponse.isDone())) {
			try {
				Thread.sleep(10); // Check status at every 10 ms
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error("Error while waiting to complete", e);
			}
		}
	}
	
	/**
	 * Handle product service error.
	 *
	 * @param productResponse the product response
	 * @return the api response body
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	private ApiResponseBody<ProductDetail> handleProductServiceError(Future<BaseServiceResponse> productResponse)
			throws InterruptedException, ExecutionException {
		ResponseBuilder<ProductDetail> errorResponse = new ApiResponseBody.ResponseBuilder<>();
		productResponse.get().getErrors().stream().forEach(errorResponse::addMoreError);
		return errorResponse.build();
	}

	 
}
