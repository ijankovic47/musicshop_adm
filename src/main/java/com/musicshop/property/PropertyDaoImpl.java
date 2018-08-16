package com.musicshop.property;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;
import com.musicshop.rest.GenericDaoImpl;

@Repository
public class PropertyDaoImpl extends GenericDaoImpl<Property, Integer> implements PropertyDao {

	public PropertyDaoImpl() {
		super("/property", Property.class);
	}

	@Override
	public List<Property> read(Integer typeId, Integer brandId, Double priceMin, Double priceMax,
			boolean havingInstruments) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).queryParam("typeId", typeId)
				.queryParam("brandId", brandId).queryParam("priceMin", priceMin).queryParam("priceMax", priceMax)
				.queryParam("havingInstruments", havingInstruments);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Property[].class);
		Property[] result = (Property[]) response.getBody();

		return Arrays.asList(result);
	}

}
