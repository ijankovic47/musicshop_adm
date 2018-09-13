package com.musicshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping()
	public String login(HttpServletRequest request, @RequestParam(name = "error", required = false) String error,
			Model model) {

		Object lastException = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (lastException != null) {
			model.addAttribute("failMessage", ((Exception) lastException).getMessage());
		}
		String referrer = request.getHeader("Referer");
		if (referrer != null && !referrer.contains("login") && !referrer.contains("customer")) {
			request.getSession().setAttribute("url_prior_login", referrer);
		}
		request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		return "login";
	}
}