package com.musicshop.rest;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class GenericDaoImpl<T, PK> implements GenericDao<T, PK> {

	@Value("${musicshop.api.url}")
	protected URI musicshopApiUrl;
	@Autowired
	protected RestTemplate restTemplate;
	protected String apiPath;
	private Class<T> type;

	public GenericDaoImpl(String apiPath, Class<T> type) {
		this.type = type;
		this.apiPath=apiPath;
	}

	public List<T> readAll() {

		ResponseEntity<?> response = restTemplate.getForEntity(buildURI(), List.class);
		List<T> result=(List<T>) response.getBody();
		return result;
	}

	protected URI buildURI() {
		return UriComponentsBuilder.fromUri(this.musicshopApiUrl).path(this.apiPath).build().toUri();
	}
}
