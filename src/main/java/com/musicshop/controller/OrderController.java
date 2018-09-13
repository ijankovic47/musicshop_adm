package com.musicshop.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.musicshop.mail.MailSendingService;
import com.musicshop.shoppingcart.ShoppingCartService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private ShoppingCartService shoppingCartService;
	private MailSendingService mailSendingService;

	@Autowired
	public OrderController(ShoppingCartService shoppingCartService, MailSendingService mailSendingService) {
		this.shoppingCartService = shoppingCartService;
		this.mailSendingService=mailSendingService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String makeOrder() throws MessagingException {
		mailSendingService.sendInstrumentsOrderMail(shoppingCartService.getInstrumentToOrder());
		shoppingCartService.clearShoppingCart();
		return "redirect:/";
	}
}
