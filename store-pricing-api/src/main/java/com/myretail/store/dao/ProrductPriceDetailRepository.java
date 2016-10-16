package com.myretail.store.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myretail.store.entity.ProrductPriceDetail;

/**
 * The Interface ProrductPriceDetailRepository.
 */
@Repository
public interface ProrductPriceDetailRepository extends MongoRepository<ProrductPriceDetail, String> {
    
    /**
     * Find by product id.
     *
     * @param productId the product id
     * @return the prorduct price detail
     */
    public ProrductPriceDetail findByProductId(Long productId);

}