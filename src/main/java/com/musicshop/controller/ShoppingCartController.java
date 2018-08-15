package com.musicshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicshop.shoppingcart.ShoppingCart;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	private ShoppingCart shoppingCart;

	@Autowired
	public ShoppingCartController(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@RequestMapping()
	public ResponseEntity<?> getShoppingCart() throws JsonProcessingException {
		return ResponseEntity.ok(new ObjectMapper().writeValueAsString(shoppingCart.getItems()));
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
	public ResponseEntity<?> addToCart(@PathVariable("itemId") String itemId,
			@RequestBody Map<Integer, Integer> itemAmount) throws JsonProcessingException {
		shoppingCart.addItem(itemAmount.entrySet().iterator().next().getKey(),
				itemAmount.entrySet().iterator().next().getValue());
		return ResponseEntity.ok(new ObjectMapper().writeValueAsString(shoppingCart.getItems()));
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeFromCart(@PathVariable("itemId") Integer itemId) throws JsonProcessingException {

		shoppingCart.removeItem(itemId);
		return ResponseEntity.ok(new ObjectMapper().writeValueAsString(shoppingCart.getItems()));
	}
}
