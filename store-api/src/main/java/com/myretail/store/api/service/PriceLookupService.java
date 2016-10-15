/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.PriceServiceResponse;

/**
 * The Class PriceLookupService.
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@Service
public class PriceLookupService {

    private static final Logger logger = LoggerFactory.getLogger(PriceLookupService.class);
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    /**
     * Instantiates a new price lookup service.
     *
     * @param restTemplateBuilder the rest template builder
     */
    public PriceLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Find product detail.
     *
     * @param productId the product id
     * @return the future
     */
    @Async
    public Future<BaseServiceResponse> findProductDetail(Long productId)  {
    	PriceServiceResponse response = new PriceServiceResponse();
    	try{
    		logger.info("Looking up pricing details by Product Id : " + productId);
	        response.setCurrencyCode("en_uss");
	        response.setValue(12.2323);
    	}catch(Exception e){
    		logger.error("Error while looking up product details",e);
    		throw new LookupServiceException("Error while looking up price details from PriceLookupService ");
    	}
        return new AsyncResult<>(response);
    }

}