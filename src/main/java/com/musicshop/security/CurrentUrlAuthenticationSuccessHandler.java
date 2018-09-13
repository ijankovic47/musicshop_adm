package com.musicshop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CurrentUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if (authentication != null) {
	        System.out.println(authentication.getName());
	    }
	    response.setStatus(HttpStatus.OK.value());
	    String url = (String) request.getSession().getAttribute("url_prior_login");
	    System.out.println(url);
	    response.sendRedirect(url!=null?url:"/");
	}

}
