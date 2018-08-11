package com.musicshop.family;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import com.musicshop.rest.GenericDaoImpl;

@Repository
public class FamilyDaoImpl extends GenericDaoImpl<Family, Integer> implements FamilyDao{

	public FamilyDaoImpl() {
		super("/family", Family.class);
	}

	@Override
	public List<Family> read(Integer brandId) {
		
		UriComponentsBuilder uri=UriComponentsBuilder.fromHttpUrl(buildURI().toString()).queryParam("brandId", brandId);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Family[].class);
		Family[] result=(Family[]) response.getBody();
		
		return Arrays.asList(result);
	}

}
