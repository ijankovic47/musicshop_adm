package com.musicshop.type;

import java.util.List;

import com.musicshop.rest.GenericDao;

public interface TypeDao extends GenericDao{

	List<Type> readAll();
}
