package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musicshop.shoppingcart.ShoppingCart;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	private ShoppingCart shoppingCart;
	
	@Autowired
	public ShoppingCartController(ShoppingCart shoppingCart) {
		this.shoppingCart=shoppingCart;
	}
	@RequestMapping()
	public ResponseEntity<?> getShoppingCart() {
		
		return ResponseEntity.ok(shoppingCart);
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.PUT)
	public ResponseEntity<?> addToCart(@PathVariable("itemId") String itemId) {
		
		shoppingCart.addItem(itemId);
		return ResponseEntity.ok(shoppingCart);
	}
	@RequestMapping(value="/{itemId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removeFromCart(@PathVariable("itemId") String itemId) {
		
		shoppingCart.removeItem(itemId);
		return ResponseEntity.ok(shoppingCart);
	}
}
