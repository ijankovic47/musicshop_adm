package com.musicshop.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicshop.instrument.Instrument;
import com.musicshop.instrument.InstrumentDao;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	private ShoppingCart shoppingCart;
	private InstrumentDao instrumentDao;
	
	@Autowired
	public ShoppingCartServiceImpl(ShoppingCart shoppingCart, InstrumentDao instrumentDao) {
		this.instrumentDao=instrumentDao;
		this.shoppingCart=shoppingCart;
	}

	@Override
	public Map<Instrument, Integer> getInstrumentToOrder() {
		
		if(shoppingCart.getItems().isEmpty()) {
			return new HashMap<>();
		}
		Map<Instrument, Integer> result=new HashMap<>();
		List<Instrument> instrumentsToOrder=instrumentDao.readByIds(new ArrayList<>(shoppingCart.getItems().keySet()));
		instrumentsToOrder.forEach(instrument->result.put(instrument, shoppingCart.getItems().get(instrument.getId())));
		return result;
	}

	@Override
	public void clearShoppingCart() {
		shoppingCart.setItems(new HashMap<Integer, Integer>());
	}

	@Override
	public Map<Integer, Integer> getItems() {
		return shoppingCart.getItems();
	}

	@Override
	public void addItem(Integer itemId, Integer amount) {
		shoppingCart.addItem(itemId, amount);
	}

	@Override
	public void removeItem(Integer itemId) {
		shoppingCart.removeItem(itemId);
	}
}
