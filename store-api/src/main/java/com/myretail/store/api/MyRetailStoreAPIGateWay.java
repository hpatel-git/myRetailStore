/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class MyRetailStoreAPIGateWay.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 */

@SpringBootApplication
@RestController
@EnableWebSecurity
@EnableAsync
public class MyRetailStoreAPIGateWay {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MyRetailStoreAPIGateWay.class, args);
	}

}
