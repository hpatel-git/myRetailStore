package com.myretail.store.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myretail.store.entity.PriceDetail;

/**
 * The Interface PriceDetailRepository.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@Repository
public interface PriceDetailRepository extends MongoRepository<PriceDetail, String> {

	/**
	 * Find by product id.
	 *
	 * @param productId
	 *            the product id
	 * @return the prorduct price detail
	 */
	public PriceDetail findByProductId(Long productId);

}