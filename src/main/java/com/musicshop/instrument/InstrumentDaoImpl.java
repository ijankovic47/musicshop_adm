package com.musicshop.instrument;

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
	public List<Instrument> read(Integer familyId, Integer typeId, Integer propertyId, Integer brandId) {
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
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), List.class);
		List<Instrument> result = (List<Instrument>) response.getBody();

		return result;
	}

}
