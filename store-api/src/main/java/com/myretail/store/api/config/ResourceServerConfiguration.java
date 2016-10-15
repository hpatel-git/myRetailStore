/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * The Class ResourceServerConfiguration extends ResourceServerConfigurerAdapter to override 
 * custom security for API gateway. Not all APIs can be accessible by guest users. This class will impose
 * URL based Authorization. For some case , only few HTTP methods are authorized.
 * 
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since   10/15/2016
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	/** Service Provider Id setup on Oauth2 Identity Server */
	private static final String RESOURCE_ID = "store-oauth2-resource";

	/*  
	 * Override configure method to protect only PATCH method on /v1/products end point. All other APIs can be accessible 
	 * by anonymous user. 
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers(HttpMethod.PATCH, "/v1/products/**").and().authorizeRequests()   // Only protect PATCH operation on /v1/product
				.anyRequest().access("#oauth2.hasScope('openid') and hasRole('ROLE_ADMIN')");
	}

	/*  
	 * Override default resource id and set service provider id which set on Oauth2 Identity Server.
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID);
	}

}
