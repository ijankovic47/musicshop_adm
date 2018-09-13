package com.musicshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CurrentUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	 public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse 
	     response, Authentication authentication)
	        throws IOException, ServletException {
	    if (authentication != null) {
	        System.out.println(authentication.getName());
	    }
	    response.setStatus(HttpStatus.OK.value());
	    response.sendRedirect(request.getHeader("referer"));
	}
}
