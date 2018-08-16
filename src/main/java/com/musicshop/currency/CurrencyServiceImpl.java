package com.musicshop.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService{

	private CurrencyDao currencyDao;
	
	@Autowired
	public CurrencyServiceImpl(CurrencyDao currencyDao) {
		this.currencyDao=currencyDao;
	}
	
	@Override
	public Double EURtoRSD(Double priceEUR) {
		if(priceEUR==null) {
			return null;
		}
		return currencyDao.getRSDforEUR()*priceEUR;
	}

	@Override
	public Double RSDtoEUR(Double priceRSD) {
		if(priceRSD==null) {
			return null;
		}
		return priceRSD/currencyDao.getRSDforEUR();
	}

}
