package com.musicshop.shoppingcart;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class ShoppingCart {

	private Map<Integer, Integer> items = new HashMap<>();

	public void addItem(Integer item, Integer amount) {

		items.put(item, amount);
	}

	public void removeItem(Integer itemId) {

		items.remove(itemId);
	}
	@JsonAnyGetter
	public Map<Integer, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Integer> items) {
		this.items = items;
	}
}
