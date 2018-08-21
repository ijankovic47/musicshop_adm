package com.musicshop.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService{

	private CurrencyDao currencyDao;
	private Double currency;
	
	@Autowired
	public CurrencyServiceImpl(CurrencyDao currencyDao) {
		this.currencyDao=currencyDao;
		updateCurrency();
	}
	
	@Override
	public Double EURtoRSD(Double priceEUR) {
		if(priceEUR==null) {
			return null;
		}
		return currency*priceEUR;
	}

	@Override
	public Double RSDtoEUR(Double priceRSD) {
		if(priceRSD==null) {
			return null;
		}
		return priceRSD/currency;
	}
	@Scheduled(cron="0 0 12 * * *")
	private void updateCurrency() {
		this.currency=currencyDao.getRSDforEUR();
		System.out.println(currency);
	}

}
