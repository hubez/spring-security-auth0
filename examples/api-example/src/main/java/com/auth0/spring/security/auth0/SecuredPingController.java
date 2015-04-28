package com.auth0.spring.security.auth0;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecuredPingController {
	private final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/secured/ping")
	@ResponseBody
	public String securedPing() {
		Object principalObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if(principalObject instanceof UserDetails){
            userDetails = (UserDetails) principalObject;
        }
        logger.info("Username: " + userDetails.getUsername());
		return "All good. You only get this message if you're authenticated. " + userDetails;
	}
}
