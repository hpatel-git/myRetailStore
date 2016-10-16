/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.PriceServiceResponse;

/**
 * The Class PriceLookupService.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@Service
public class PriceLookupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceLookupService.class);

	@Value("${price.lookup.url}")
	private String priceLookupUrl;

	private final RestTemplate restTemplate;

	/**
	 * Instantiates a new price lookup service.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 */
	public PriceLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	/**
	 * Find product detail.
	 *
	 * @param productId
	 *            the product id
	 * @return the future
	 */
	@Async
	public Future<BaseServiceResponse> findProductDetail(Long productId) {
		PriceServiceResponse response = null;
		try {
			LOGGER.info("Looking up pricing details by Product Id : " + productId);
			String url = String.format(priceLookupUrl, productId);
			response = restTemplate.getForObject(url, PriceServiceResponse.class);
		} catch (Exception e) {
			LOGGER.error("Error while looking up product details", e);
			throw new LookupServiceException("Price Lookup Service is unavailable");
		}
		return new AsyncResult<>(response);
	}

}