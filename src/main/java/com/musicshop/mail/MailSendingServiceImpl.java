package com.musicshop.mail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.musicshop.customer.Customer;
import com.musicshop.instrument.Instrument;

@Service
public class MailSendingServiceImpl implements MailSendingService {

	private JavaMailSender javaMailSender;
	private Environment environment;
	private HttpServletRequest request;

	@Autowired
	public MailSendingServiceImpl(JavaMailSender javaMailSender, Environment environment, HttpServletRequest request) {
		this.javaMailSender = javaMailSender;
		this.environment = environment;
		this.request=request;
	}

	@Override
	public void sendInstrumentsOrderMail(Map<Instrument, Integer> instrumentAmount) throws MessagingException {
		if (!instrumentAmount.isEmpty()) {
			Customer c = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			String[] receivers = environment.getProperty("order.mail.receivers").split(",");

			String content = "<table border='1'><tr><th>Instrument</th><th>Image</th><th>Amount</th><th>Price</th>";
			for (Entry<Instrument, Integer> e : instrumentAmount.entrySet()) {
				content += "<tr><td>" + e.getKey().getName() + "</td><td><img src='" + e.getKey().getImages().get(0)
						+ "' style='width:150px;height:150px'/></td><td>" + e.getValue() + "</td><td>"
						+ e.getKey().getPrice() * e.getValue() + "</td></tr>";
			}
			content += "</table><h2>Customer details</h2><ul>" + "<li>first name: " + c.getFirstName() + "</li>"
					+ "<li>last name: " + c.getLastName() + "</li>" + "<li>email: " + c.getUsername() + "</li>"
					+ "<li>phone: " + c.getPhone() + "</li>" + "<li>country: " + c.getCountry() + "</li>" 
					+ "<li>city: "+ c.getCity() + "</li>" 
							+ "<li>street and no: "+c.getStreetAndNumber()+"</li>"
					+ "<li>postal: " + c.getPostal() + "</li>" + "</ul>";
			helper.setTo(receivers);
			helper.setSubject("Instruments order list (from: " + c.getUsername() + ")");
			helper.setReplyTo(c.getUsername());
			mimeMessage.setContent(content, "text/html");
			javaMailSender.send(mimeMessage);
		}
	}
 
	@Override
	public void sendEmailConfirmation(String email) throws MessagingException, URISyntaxException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		URI requestUri = new URI(request.getRequestURL().toString());
		URI contextUri = new URI(requestUri.getScheme(), 
		                         requestUri.getAuthority(), 
		                         request.getContextPath(), 
		                         null, 
		                         null);
		String content="<form method='post' action='"+contextUri+"/customer/"+email+"/'>"
				+ "<input type='hidden' value='true' name='active'/><input type='hidden' value='JSESSIONID="+request.getSession().getId()+"' name='Cookie'/><input type='submit' value='Confirm email and log in'></form>";
		helper.setTo(email);
		helper.setSubject("Email confirmation");
		mimeMessage.setContent(content, "text/html");
		javaMailSender.send(mimeMessage);
	}

}
