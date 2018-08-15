package com.musicshop.family;

import java.util.List;

import com.musicshop.rest.GenericDao;

public interface FamilyDao extends GenericDao<Family, Integer>{

	List<Family> read(Integer brandId, Integer priceMin, Integer priceMax, boolean havingInstruments);
}
