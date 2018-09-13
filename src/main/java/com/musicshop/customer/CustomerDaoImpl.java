package com.musicshop.customer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import com.musicshop.rest.GenericDaoImpl;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, String> implements CustomerDao {

	public CustomerDaoImpl() {
		super("/customer", Customer.class);
	}

//	@Override
//	public Customer readByEmail(String email) {
//		
//		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).path("/" + email);
//		ResponseEntity<Customer> response = restTemplate.getForEntity(uri.toUriString(), Customer.class);
//		Customer customer = response.getBody();
//		return customer;
//	}

	@Override
	public void create(Customer customer) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
	        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString());
	        restTemplate.exchange(uri.toUriString(), HttpMethod.POST, requestEntity, String.class);
	}

	@Override
	public void active(String email, boolean active) {
		
		restTemplate.exchange(buildURI().toString()+"/"+email+"/", HttpMethod.PATCH,new HttpEntity<Boolean>(active), String.class);
	}

}
