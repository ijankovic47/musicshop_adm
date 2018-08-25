package com.musicshop.brand;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import com.musicshop.rest.GenericDaoImpl;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand, Integer> implements BrandDao {

	public BrandDaoImpl() {
		super("/brand", Brand.class);
	}

	@Override
	public List<Brand> read(Integer familyId, Integer typeId, Integer propertyId, Double priceMin, Double priceMax,
			boolean havingInstruments, BrandSort sort) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString())
				.queryParam("familyId", familyId).queryParam("typeId", typeId).queryParam("propertyId", propertyId)
				.queryParam("priceMin", priceMin).queryParam("priceMax", priceMax)
				.queryParam("havingInstruments", havingInstruments)
				.queryParam("sort", sort);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Brand[].class);
		Brand[] result = (Brand[]) response.getBody();

		return Arrays.asList(result);
	}

}
