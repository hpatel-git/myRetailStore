/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.ProductServiceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductLookupServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductLookupServiceTest.class);
	 
	@Autowired
	private ProductLookupService productLookupService;


	@Before
	public void setup() throws Exception {
		
	}

	@Test
	public void testFindProductDetail() throws Exception {
		LOGGER.debug("PriceLookupServiceTest.testFindProductDetail ");

		Future<BaseServiceResponse> response = productLookupService.findProductDetail(15117729l);
		assertNotNull(response);
		ProductServiceResponse priceServiceResponse = (ProductServiceResponse) response.get();
		assertTrue(priceServiceResponse.getProductId() == (15117729l));
		assertNotNull(priceServiceResponse.getProductName());

	}

	@Test
	public void testFindInvalidProductId() throws Exception {
		LOGGER.debug("PriceLookupServiceTest.testFindProductDetail");
		Future<BaseServiceResponse> response = productLookupService.findProductDetail(-15117729l);
		assertNotNull(response);
		ProductServiceResponse priceServiceResponse = (ProductServiceResponse) response.get();
		assertNotNull(priceServiceResponse.getErrors());
		System.out.println(priceServiceResponse.getErrors().stream().findFirst().get().getMessage());
		assertTrue(priceServiceResponse.getErrors().stream().findFirst().get().getMessage().contains("Not a valid ID")); 
	}
	 

	@Test
	public void testFindProductIdNotFound() throws Exception {
		LOGGER.debug("PriceLookupServiceTest.testFindProductIdNotFound ");
		Future<BaseServiceResponse> response = productLookupService.findProductDetail(123l);
		assertNotNull(response);
		ProductServiceResponse priceServiceResponse = (ProductServiceResponse) response.get();
		assertNotNull(priceServiceResponse.getErrors());
		System.out.println(priceServiceResponse.getErrors().stream().findFirst().get().getMessage());
		assertTrue(priceServiceResponse.getErrors().stream().findFirst().get().getMessage().contains("Not valid product in system: This product ID does not represent a valid product")); 

	}
}
