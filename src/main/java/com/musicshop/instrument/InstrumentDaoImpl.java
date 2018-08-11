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
		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString())
				.queryParam("familyId", familyId).queryParam("typeId", typeId).queryParam("propertyId", propertyId)
				.queryParam("brandId", brandId).queryParam("pageSize", pageSize).queryParam("pageNumber", pageNumber)
				.queryParam("priceMin", priceMin).queryParam("priceMax", priceMax);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Instrument[].class);
		Instrument[] instruments = (Instrument[]) response.getBody();

		return Arrays.asList(instruments);
	}

	@Override
	public List<Double> prices(Integer familyId, Integer typeId, Integer propertyId, Integer brandId, Integer priceMin,
			Integer priceMax) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).path("/count")
				.queryParam("familyId", familyId).queryParam("typeId", typeId).queryParam("propertyId", propertyId)
				.queryParam("brandId", brandId).queryParam("priceMin", priceMin).queryParam("priceMax", priceMax);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Double[].class);
		Double[] result = (Double[]) response.getBody();

		return Arrays.asList(result);
	}

}
