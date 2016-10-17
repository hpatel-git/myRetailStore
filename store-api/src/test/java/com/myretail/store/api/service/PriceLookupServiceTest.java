/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.store.api.model.BaseServiceResponse;
import com.myretail.store.api.model.PriceServiceResponse;
import com.myretail.store.api.model.PriceUpdateRequest;
/**
 * ProductResource.
 *
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since   10/15/2016
 */
import com.myretail.store.api.service.PriceLookupService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PriceLookupServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(PriceLookupServiceTest.class);

	protected ObjectMapper mapper;
	 
	private PriceLookupService priceLookupService;

	private MockRestServiceServer server;

	@Before
	public void setup() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		server = MockRestServiceServer.createServer(restTemplate);
		mapper = new ObjectMapper();
		PriceServiceResponse priceServiceResponse = new PriceServiceResponse();
		priceServiceResponse.setPrice(new BigDecimal("12.12"));
		priceServiceResponse.setCurrencyCode(Currency.getInstance("USD"));
		String detailsString = mapper.writeValueAsString(priceServiceResponse);
		server.expect(requestTo("http://@price.lookup.host%40/@price.lookup.port@/pricing-api/v1/products/15117729/price"))
				.andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
		RestTemplateBuilder builder  = new RestTemplateBuilder(); 
		priceLookupService = new PriceLookupService(builder);
		ReflectionTestUtils.setField(priceLookupService, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(priceLookupService, "priceLookupUrl", "http://@price.lookup.host@:@price.lookup.port@/pricing-api/v1/products/%d/price");

	}

	@Test
	public void testFindProductDetail() throws Exception {
		LOGGER.debug("PriceLookupServiceTest.testFindProductDetail : Upate Product Detail By Product Id ");

		Future<BaseServiceResponse> response = priceLookupService.findPriceDetail(15117729l);
		assertNotNull(response);
		PriceServiceResponse priceServiceResponse = (PriceServiceResponse) response.get();
		assertTrue(priceServiceResponse.getCurrencyCode().equals(Currency.getInstance("USD")));
		assertTrue(priceServiceResponse.getPrice().equals(new BigDecimal("12.12")));

	}

	@Test
	public void testUpdateProductDetail() throws Exception {
		LOGGER.debug("PriceLookupServiceTest.testUpdateProductDetail : Update Price Detail By Product Id ");
		PriceUpdateRequest priceUpdateReq = new PriceUpdateRequest();
		priceUpdateReq.setProductId(15117729l);
		priceUpdateReq.setPrice(new BigDecimal("45.23"));
		
		Future<BaseServiceResponse> response = priceLookupService.updatePrice(priceUpdateReq);
		assertNotNull(response);

	}
	 

}
