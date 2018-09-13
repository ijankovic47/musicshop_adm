package com.musicshop.rest;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
		this.apiPath = apiPath;
	}

	public List<T> readAll() {

		ResponseEntity<?> response = restTemplate.getForEntity(buildURI(), List.class);
		List<T> result = (List<T>) response.getBody();
		return result;
	}

	protected URI buildURI() {
		return UriComponentsBuilder.fromUri(this.musicshopApiUrl).path(this.apiPath).build().toUri();
	}

	@Override
	public T readById(PK id) {
	
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildURI()+"/"+id+"/");
		ResponseEntity<?> response = restTemplate.getForEntity(builder.toUriString(), type);
		if(response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			throw new BadCredentialsException((String) response.getBody());
		}
		T result =  (T) response.getBody();
		return result;
	}
}
