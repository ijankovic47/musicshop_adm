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
			Integer pageSize, Integer pageNumber, Double priceMin, Double priceMax, InstrumentSort sort) {
		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString())
				.queryParam("familyId", familyId).queryParam("typeId", typeId).queryParam("propertyId", propertyId)
				.queryParam("brandId", brandId).queryParam("pageSize", pageSize).queryParam("pageNumber", pageNumber)
				.queryParam("priceMin", priceMin).queryParam("priceMax", priceMax).queryParam("sort", sort!=null?sort.name():null);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Instrument[].class);
		Instrument[] instruments = (Instrument[]) response.getBody();

		return Arrays.asList(instruments);
	}

	@Override
	public List<Double> prices(Integer familyId, Integer typeId, Integer propertyId, Integer brandId, Double priceMin,
			Double priceMax) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).path("/count")
				.queryParam("familyId", familyId).queryParam("typeId", typeId).queryParam("propertyId", propertyId)
				.queryParam("brandId", brandId).queryParam("priceMin", priceMin).queryParam("priceMax", priceMax);
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Double[].class);
		Double[] result = (Double[]) response.getBody();

		return Arrays.asList(result);
	}

	@Override
	public Instrument readById(Integer id) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString()).path("/" + id);
		ResponseEntity<Instrument> response = restTemplate.getForEntity(uri.toUriString(), Instrument.class);
		Instrument instrument = response.getBody();
		return instrument;
	}

	@Override
	public List<Instrument> readByIds(List<Integer> ids) {

		UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(buildURI().toString());
		for (Integer id : ids) {
			uri.queryParam("ids", id);
		}
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), Instrument[].class);
		Instrument[] instruments = (Instrument[]) response.getBody();

		return Arrays.asList(instruments);
	}

}
