package com.musicshop.shoppingcart;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonValue;

public class ShoppingCart {

	private Map<String, Integer> items = new HashMap<>();

	public void addItem(String itemId) {

		items.putIfAbsent(itemId, 1);
	}

	public void removeItem(String itemId) {

		items.remove(itemId);
	}
	@JsonAnyGetter
	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
	@JsonValue
	public String toJson() {
		String result="{";
		for(Entry<String,Integer> e:items.entrySet()) {
			result+=",'"+e.getKey()+"':'"+e.getValue()+"'";
		}
		result+="}";
		return result.replaceFirst(",", "");
	}
}
