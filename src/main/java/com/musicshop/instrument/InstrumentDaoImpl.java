package com.musicshop.instrument;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;
import com.musicshop.rest.GenericDaoImpl;

@Repository
public class InstrumentDaoImpl extends GenericDaoImpl<Instrument, Integer> implements InstrumentDao {

	public InstrumentDaoImpl() {
		super("/instrument", Instrument.class);
	}

	@Override
	public List<Instrument> read(Integer familyId, Integer typeId, Integer propertyId, Integer brandId,
			Integer pageSize, Integer pageNumber, Integer priceMin, Integer priceMax) {
		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString());
		if (familyId != null) {
			uri.queryParam("familyId", familyId);
		}
		if (typeId != null) {
			uri.queryParam("typeId", typeId);
		}
		if (propertyId != null) {
			uri.queryParam("propertyId", propertyId);
		}
		if (brandId != null) {
			uri.queryParam("brandId", brandId);
		}
		uri.queryParam("pageSize", pageSize);
		uri.queryParam("pageNumber", pageNumber);
		
		if (priceMin != null) {
			uri.queryParam("priceMin", priceMin);
		}
		if (priceMax != null) {
			uri.queryParam("priceMax", priceMax);
		}
		
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Instrument[].class);
		Instrument[] instruments =  (Instrument[]) response.getBody();

		return Arrays.asList(instruments);
	}

	@Override
	public List<Double> prices(Integer familyId, Integer typeId, Integer propertyId, Integer brandId, Integer priceMin, Integer priceMax) {
		
		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).path("/count");
		if (familyId != null) {
			uri.queryParam("familyId", familyId);
		}
		if (typeId != null) {
			uri.queryParam("typeId", typeId);
		}
		if (propertyId != null) {
			uri.queryParam("propertyId", propertyId);
		}
		if (brandId != null) {
			uri.queryParam("brandId", brandId);
		}
		if (priceMin != null) {
			uri.queryParam("priceMin", priceMin);
		}
		if (priceMax != null) {
			uri.queryParam("priceMax", priceMax);
		}
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Double[].class);
		Double[] result = (Double[]) response.getBody();

		return Arrays.asList(result);
	}

}
