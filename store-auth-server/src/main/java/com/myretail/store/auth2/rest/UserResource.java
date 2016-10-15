/**
 * @ copyright 2016, myRetail Corporation.
 */
package com.myretail.store.auth2.rest;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.store.auth2.model.UserProfile;

/**
 * The Class UserResource.
 *
 * @author Hardikkumar patel(hardikkumar.ce@gmail.com)
 * @version 1.0
 * @since 10/15/2016
 * 
 */
@RestController
@RequestMapping("/v1/user")
@EnableResourceServer
public class UserResource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	/**
	 * User.
	 *
	 * @param user
	 *            the user
	 * @return the principal
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

	/**
	 * Gets the user locale.
	 *
	 * @param request
	 *            the request
	 * @param principal
	 *            the principal
	 * @return the user locale
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/locale", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody public UserProfile getUserLocale(HttpServletRequest request, Principal principal) {
		UserProfile profile = new UserProfile();
		String defaultLocale = "en_US";
		if (request.getSession().getAttribute("defaultLocale") != null) {
			defaultLocale = (String) request.getSession().getAttribute("defaultLocale");
		}
		profile.setUserName(principal.getName());
		profile.setLocale(defaultLocale);
		LOGGER.debug("UserResource.getUserLocal : " + defaultLocale);
		return profile;
	}

}
