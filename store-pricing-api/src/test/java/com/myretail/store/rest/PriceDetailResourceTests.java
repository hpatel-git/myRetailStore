/**
 * @ copyright 2016, myRetail Corporation.
 */

package com.myretail.store.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.store.entity.PriceDetail;

/**
 * The Class PriceDetailResource.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceDetailResourceTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceDetailResourceTests.class);
	
	protected MockMvc mockMvc;
	protected ObjectMapper mapper;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testFindPriceDetailsByProductId() throws Exception {
		LOGGER.debug("PriceDetailResourceTests.testFindPriceDetailsByProductId");
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/products/15117729/price")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8")).andReturn();
		PriceDetail priceDetail = mapper.readValue(result.getResponse().getContentAsString(),
				PriceDetail.class);
		assertNotNull(priceDetail);
		assertNotNull(priceDetail.getCurrencyCode());
		assertNotNull(priceDetail.getPrice());
	}
	
	@Test
	public void testUpdatePriceDetailsByProductId() throws Exception {
		LOGGER.debug("PriceDetailResourceTests.testUpdatePriceDetailsByProductId ");
		
		PriceDetail updatedReq = new PriceDetail();
		updatedReq.setProductId(15117729l);
		updatedReq.setPrice(new BigDecimal("23.12"));
		updatedReq.setCurrencyCode(Currency.getInstance("USD"));
		mockMvc
				.perform(MockMvcRequestBuilders.put("/v1/products/15117729/price").
						content(mapper.writeValueAsString(updatedReq))
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				//.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/products/15117729/price")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		PriceDetail priceDetail = mapper.readValue(result.getResponse().getContentAsString(),
				PriceDetail.class);
		assertNotNull(priceDetail);
		assertNotNull(priceDetail.getCurrencyCode());
		assertNotNull(priceDetail.getPrice());
		assertEquals(new BigDecimal("23.12"),priceDetail.getPrice()); 
	}
	
	@Test
	public void testFindPriceDetailsByProductIdNotFound() throws Exception {
		LOGGER.debug("PriceDetailResourceTests.testFindPriceDetailsByProductIdNotFound ");
		String error = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/products/1523200117729/price")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn().getResolvedException().getMessage();
		assertNotNull(error);
		assertEquals("Price for product 1523200117729 not found.", error);
 
	}
	
	
}
