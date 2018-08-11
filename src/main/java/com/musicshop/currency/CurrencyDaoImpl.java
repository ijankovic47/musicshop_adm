package com.musicshop.currency;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class CurrencyDaoImpl implements CurrencyDao {

	private RestTemplate restTemplate;
	@Value("${currency.converter.api}")
	private String currencyConverterApi;

	@Autowired
	public CurrencyDaoImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Double getRSDforEUR() {

		URI uri = UriComponentsBuilder.fromUriString(this.currencyConverterApi).build().toUri();
		ResponseEntity<?> response = restTemplate.getForEntity(uri, String.class);
		String resp = response.getBody().toString();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < resp.length(); i++) {

			char c = resp.charAt(i);
			if (Character.isDigit(c) || c == '.') {
				result.append(c);
			}
		}
		return Double.valueOf(result.toString());
	}

}
