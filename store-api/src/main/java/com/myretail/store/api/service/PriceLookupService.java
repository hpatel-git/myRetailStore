/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.store.api.exception.BadRequestException;
import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.PriceServiceErrorRes;
import com.myretail.store.api.model.PriceServiceResponse;
import com.myretail.store.api.model.PriceUpdateRequest;

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

	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	
	  
	
	/**
	 * Instantiates a new price lookup service.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 */
	public PriceLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		objectMapper = new ObjectMapper();
	}

	/**
	 * Find product detail.
	 *
	 * @param productId
	 *            the product id
	 * @return the future
	 */
	@Async
	public Future<BaseServiceResponse> findPriceDetail(Long productId) {
		PriceServiceResponse response = new PriceServiceResponse();
		try {
			LOGGER.info("Looking up pricing details by Product Id : " + productId);
			String url = String.format(priceLookupUrl, productId);
			response = restTemplate.getForObject(url, PriceServiceResponse.class);
		} catch(HttpClientErrorException e){
			LOGGER.error("Error while finding PriceDetail",e);
			String errorMsg = null;
			try{
				PriceServiceErrorRes error = objectMapper.readValue(e.getResponseBodyAsString(), PriceServiceErrorRes.class);
				errorMsg = error.getMessage();
			}catch(Exception error){ 
				LOGGER.error("Error while parsing response from Pricing Service",error);
				errorMsg = error.getMessage();
			}
			response.addError(errorMsg);
		} catch (Exception e) {
			LOGGER.error("Error while looking up product details", e);
			throw new LookupServiceException("Price Lookup Service is unavailable");
		}
		return new AsyncResult<>(response);
	}
	
	@Async
	public Future<BaseServiceResponse> updatePrice(PriceUpdateRequest priceUpdateReq) {
		PriceServiceResponse response = new PriceServiceResponse();
		try {
			LOGGER.info("Looking up pricing details by Product Id : " + priceUpdateReq.getProductId());
			String url = String.format(priceLookupUrl, priceUpdateReq.getProductId());
			restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<PriceUpdateRequest>(priceUpdateReq), String.class);
		}catch(HttpClientErrorException e){
			LOGGER.error("Error while updating price",e);
			String errorMsg = null;
			try{
				PriceServiceErrorRes error = objectMapper.readValue(e.getResponseBodyAsString(), PriceServiceErrorRes.class);
				errorMsg = error.getMessage();
			}catch(Exception error){ 
				LOGGER.error("Error while parsing response from Pricing Service",error);
				errorMsg = error.getMessage();
			}
			response.addError(errorMsg);
		} catch (Exception e) {
			LOGGER.error("Error while looking up product details", e);
			throw new LookupServiceException("Price Lookup Service is unavailable");
		}
		return new AsyncResult<>(response);
	}
	

}