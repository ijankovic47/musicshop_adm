package com.musicshop.property;

import java.util.List;

import com.musicshop.rest.GenericDao;

public interface PropertyDao extends GenericDao<Property, Integer>{

	List<Property> read(Integer typeId, Integer brandId, Double priceMin, Double priceMax, boolean havingInstruments);
}
