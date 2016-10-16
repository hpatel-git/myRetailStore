package com.myretail.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.store.dao.PriceDetailRepository;
import com.myretail.store.entity.PriceDetail;
import com.myretail.store.rest.PriceDetailResource;

/**
 * The Class PriceDetailService.
 */
@Service
public class PriceDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceDetailResource.class);
	@Autowired
	private PriceDetailRepository priceDetailRepository;

	/**
	 * Find by product id.
	 *
	 * @param productId the product id
	 * @return the prorduct price detail
	 */
	public PriceDetail findByProductId(Long productId) {
		LOGGER.debug("PriceDetailService.findByProductId : Finding Product Id "+productId);
		return priceDetailRepository.findByProductId(productId);
	}
	
	/**
	 * Update price details.
	 *
	 * @param priceDetail the price detail
	 */
	@Transactional(readOnly = false)
	public void updatePriceDetails(PriceDetail priceDetail) {
		LOGGER.debug("PriceDetailService.updatePriceDetails : Updating Product Price Id Details"+priceDetail.getId());
		priceDetailRepository.save(priceDetail);
	}
}
