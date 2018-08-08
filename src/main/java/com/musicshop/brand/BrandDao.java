package com.musicshop.brand;

import java.util.List;

import com.musicshop.rest.GenericDao;

public interface BrandDao extends GenericDao<Brand, Integer>{

	List<Brand> read(Integer familyId, Integer typeId);
}