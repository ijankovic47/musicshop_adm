package com.musicshop.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class GenericDaoImpl<T,PK> implements GenericDao {

	@Value("${musicshop.api.url}")
	private String musicshopApiUrl;
	@Autowired
	protected RestTemplate restTemplate;
	protected URI apiPath;
	private Class<T> type;

	public GenericDaoImpl(String apiPath, Class<T> type) {
		this.apiPath = UriComponentsBuilder.fromPath(musicshopApiUrl).path(apiPath).build().toUri();
		this.type = type;
		System.out.println(apiPath);
	}

	public List<T> readAll() {

		ResponseEntity<?> response = restTemplate.getForEntity(apiPath, type);
		System.out.println(response.getBody());
		return null;
	}
}
