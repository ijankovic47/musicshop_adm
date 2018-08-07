package com.musicshop.brand;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import com.musicshop.rest.GenericDaoImpl;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Integer> implements BrandDao{

	public BrandDaoImpl() {
		super("/brand", Brand.class);
	}

	@Override
	public List<Brand> read(Integer familyId) {
		
		UriComponentsBuilder uri=UriComponentsBuilder.fromHttpUrl(buildURI().toString()).queryParam("familyId", familyId);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), List.class);
		List<Brand> result=(List<Brand>) response.getBody();
		
		return result;
	}

}
