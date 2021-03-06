package com.musicshop.type;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import com.musicshop.rest.GenericDaoImpl;

@Repository
public class TypeDaoImpl extends GenericDaoImpl<Type, Integer> implements TypeDao {

	public TypeDaoImpl() {
		super("/type", Type.class);
	}

	@Override
	public List<Type> read(Integer familyId, Integer brandId, Double priceMin, Double priceMax,
			boolean havingInstruments) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString())
				.queryParam("familyId", familyId).queryParam("brandId", brandId).queryParam("priceMin", priceMin)
				.queryParam("priceMax", priceMax).queryParam("havingInstruments", havingInstruments);

		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Type[].class);
		Type[] result = (Type[]) response.getBody();

		return Arrays.asList(result);
	}
}
