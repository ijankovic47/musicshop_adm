package com.musicshop.shoppingcart;

import java.util.Map;

import com.musicshop.instrument.Instrument;

public interface ShoppingCartService {

	Map<Instrument, Integer> getInstrumentToOrder();
	void clearShoppingCart();
	Map<Integer, Integer> getItems();
	void addItem(Integer itemId, Integer amount);
	void removeItem(Integer itemId);
}
