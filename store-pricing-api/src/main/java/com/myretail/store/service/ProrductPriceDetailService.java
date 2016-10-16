package com.myretail.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.store.dao.ProrductPriceDetailRepository;
import com.myretail.store.entity.ProrductPriceDetail;
import com.myretail.store.rest.ProductPriceDetailResource;

/**
 * The Class ProrductPriceDetailService.
 */
@Service
public class ProrductPriceDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDetailResource.class);
	@Autowired
	private ProrductPriceDetailRepository prorductPriceDetailRepository;

	/**
	 * Find by product id.
	 *
	 * @param productId the product id
	 * @return the prorduct price detail
	 */
	public ProrductPriceDetail findByProductId(Long productId) {
		LOGGER.debug("ProrductPriceDetailService.findByProductId : Finding Product Id "+productId);
		return prorductPriceDetailRepository.findByProductId(productId);
	}
	
	/**
	 * Update price details.
	 *
	 * @param priceDetail the price detail
	 */
	@Transactional(readOnly = false)
	public void updatePriceDetails(ProrductPriceDetail priceDetail) {
		LOGGER.debug("ProrductPriceDetailService.updatePriceDetails : Updating Product Price Id Details"+priceDetail.getId());
		prorductPriceDetailRepository.save(priceDetail);
	}
}
