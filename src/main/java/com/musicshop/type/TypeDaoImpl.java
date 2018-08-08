package com.musicshop.type;

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
	public List<Type> read(Integer familyId, Integer brandId) {
		
		UriComponentsBuilder uri=UriComponentsBuilder.fromHttpUrl(buildURI().toString()).queryParam("familyId", familyId);
		if(brandId!=null) {
			uri.queryParam("brandId", brandId);
		}
		ResponseEntity<?> response = restTemplate.getForEntity(uri.toUriString(), List.class);
		List<Type> result=(List<Type>) response.getBody();
		
		return result;
	}
}
