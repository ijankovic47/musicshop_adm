package com.musicshop.rest;

import java.util.List;

public interface GenericDao<T, PK> {

	List<T> readAll();
	void saveEntity(T entity);
}
