package com.musicshop.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.musicshop.customer.Customer;
import com.musicshop.customer.CustomerDao;
import com.musicshop.mail.MailSendingService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerDao customerDao;
	private MailSendingService mailSendingService;
	private RestTemplate restTemplate;
	@Value("#{servletContext.contextPath}")
    private String servletContextPath;
	private HttpServletRequest request;
	
	@Autowired
	public CustomerController(CustomerDao customerDao, MailSendingService mailSendingService, RestTemplate restTemplate, HttpServletRequest request) {
		this.customerDao=customerDao;
		this.mailSendingService=mailSendingService;
		this.restTemplate=restTemplate;
		this.request=request;
	}
	
	@RequestMapping()
	public String register(Model model,HttpServletRequest request) {

		if(request.getSession().getAttribute("customer")!=null) {
			model.addAttribute("registrationMessage", "Confirmation link is sent to inserted email ! Please confirm to proceed.");
			model.addAttribute("customer", request.getSession().getAttribute("customer"));
		}
		else {
			model.addAttribute("customer", new Customer());
			Object lastException = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			if (lastException != null) {
				model.addAttribute("failMessage", ((Exception) lastException).getMessage());
			}
		}
		String referrer = request.getHeader("Referer");
		if (referrer != null && !referrer.contains("login") && !referrer.contains("customer")) {
			request.getSession().setAttribute("url_prior_login", referrer);
		}
		return "registration";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String confirmEmail(@ModelAttribute Customer customer) throws MessagingException, URISyntaxException {
		request.getSession().setAttribute("customer", customer);
		mailSendingService.sendEmailConfirmation(customer.getEmail());
		return "redirect:/customer";
	}
	@RequestMapping(value="/{email}", method=RequestMethod.POST)
	public ResponseEntity<?> activateEmail(@PathVariable("email") String email, @RequestParam("active") Boolean active) throws Exception {

		URI requestUri = new URI(request.getRequestURL().toString());
		URI contextUri = new URI(requestUri.getScheme(), 
		                         requestUri.getAuthority(), 
		                         request.getContextPath(), 
		                         null, 
		                         null);
		if(request.getSession().getAttribute("customer")==null) {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Cookie", "JSESSIONID=" + request.getSession().getId());
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, requestHeaders);
			request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new BadCredentialsException("Session expired! You have to register again."));
			ResponseEntity<String> out = restTemplate.exchange(contextUri.toString()+"/customer",HttpMethod.GET, requestEntity, String.class);
			return out;
		}
		Customer c=(Customer) request.getSession().getAttribute("customer");
		c.setActive(true);
		customerDao.create(c);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", "JSESSIONID=" + request.getSession().getId());
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(null, requestHeaders);
		c=customerDao.readById(email);
		
		ResponseEntity<String> out = restTemplate.exchange(contextUri.toString()+"/login?username="
				+ c.getEmail()+"&password="+c.getPassword(),HttpMethod.POST, requestEntity, String.class);
		request.getSession().removeAttribute("customer");
		return out;
		
	}
}
