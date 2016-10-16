/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import static com.myretail.store.api.utils.Constants.ERROR_NODE;
import static com.myretail.store.api.utils.Constants.ITEMS_NODE;
import static com.myretail.store.api.utils.Constants.MESSAGE_NODE;
import static com.myretail.store.api.utils.Constants.PRODUCT_DESC;
import static com.myretail.store.api.utils.Constants.ROOT_LEVEL;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.store.api.exception.LookupServiceException;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.ProductServiceResponse;

/**
 * The Class ProductLookupService.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@Service
public class ProductLookupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductLookupService.class);
	
	@Value("${product.lookup.url}")
	private String productLookupUrl;
	

	private final RestTemplate restTemplate;
	private ObjectMapper objectMapper;

	/**
	 * Instantiates a new product lookup service.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 */
	public ProductLookupService(RestTemplateBuilder restTemplateBuilder) {
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
	public Future<BaseServiceResponse> findProductDetail(Long productId) {
		ProductServiceResponse response = new ProductServiceResponse();
		try {
			LOGGER.info("Looking up product details by Product Id : " + productId);
			String url = String.format(productLookupUrl, productId);
			String responseString = restTemplate.getForObject(url, String.class);
			JsonNode root = objectMapper.readTree(responseString);
			String productDescription = root.at(ROOT_LEVEL).at(ITEMS_NODE).get(0).at(PRODUCT_DESC).asText();
			if (StringUtils.isEmpty(productDescription)) {
				root.at("/product_composite_response").at(ITEMS_NODE).get(0).at(ERROR_NODE)
						.forEach(item -> response.addError(item.at(MESSAGE_NODE).asText()));
			}
			response.setProductName(productDescription);
		} catch (Exception e) {
			LOGGER.error("Error while looking up product details", e);
			throw new LookupServiceException("Product Lookup Service is unavailable ");
		}
		return new AsyncResult<>(response);
	}

}