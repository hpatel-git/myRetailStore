package com.myretail.store;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myretail.store.dao.ProrductPriceDetailRepository;
import com.myretail.store.entity.ProrductPriceDetail;

/**
 * The Class StorePricingApiApplication.
 */
@SpringBootApplication
public class StorePricingApiApplication implements CommandLineRunner {

	@Autowired
	private ProrductPriceDetailRepository repository;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(StorePricingApiApplication.class, args);
	}

	/*
	 * Initialized collection with sample product price information Price will
	 * be between 400 and 500 USD
	 */

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	
	@Override
	public void run(String... args) throws Exception {
		List<Long> initialData = Arrays.asList(15117729l, 16483589l, 16696652l, 16752456l, 15643793l);
		double start = 400;
		double end = 500;
		initialData.forEach(item -> {
			if (repository.findByProductId(item) == null) {
				double random = new Random().nextDouble();
				double result = start + (random * (end - start));
				BigDecimal price = new BigDecimal(Double.toString(result));
				price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN); // Round to 2 decimal points
				repository.save(new ProrductPriceDetail(item, price, Currency.getInstance("USD"))); // Default conside USD code
			}

		});
	}

}
