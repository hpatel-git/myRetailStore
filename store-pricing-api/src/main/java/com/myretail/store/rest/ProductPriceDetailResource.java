package com.myretail.store.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.store.entity.ProrductPriceDetail;
import com.myretail.store.exception.ProductNotFoundException;
import com.myretail.store.service.ProrductPriceDetailService;

/**
 * The Class ProductPriceDetailResource.
 */
@RestController
@RequestMapping("/v1/product/{id}/price")
public class ProductPriceDetailResource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDetailResource.class);

	/** The prorduct price detail service. */
	@Autowired
	private ProrductPriceDetailService prorductPriceDetailService;

	/**
	 * Find price details by product id.
	 *
	 * @param productId the product id
	 * @return the prorduct price detail
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ProrductPriceDetail findPriceDetailsByProductId(@PathVariable("id") Long productId) {
		LOGGER.debug("ProductPriceDetailResource.findPriceDetailsByProductId Finding price details by Product Id "
				+ productId);
		return prorductPriceDetailService.findByProductId(productId);
	}
	
	/**
	 * Update price detail.
	 *
	 * @param productId the product id
	 * @param priceDetails the price details
	 */
	@RequestMapping(method = RequestMethod.PATCH, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePriceDetail(@PathVariable("id") Long productId, @RequestBody(required = true) ProrductPriceDetail priceDetails) {
		LOGGER.debug("ProductPriceDetailResource.findPriceDetailsByProductId Finding price details by Product Id "
				+ productId);
		ProrductPriceDetail dbProrductPriceDetail = prorductPriceDetailService.findByProductId(productId);
		if(dbProrductPriceDetail != null){
			if(priceDetails.getCurrencyCode() != null){
				dbProrductPriceDetail.setCurrencyCode(priceDetails.getCurrencyCode());
			}
			if(priceDetails.getPrice() != null){
				dbProrductPriceDetail.setPrice(priceDetails.getPrice());
			}
			prorductPriceDetailService.updatePriceDetails(dbProrductPriceDetail);
		}else{
			throw new ProductNotFoundException("Product "+productId+" not found.");
		}
	}
	 
}
