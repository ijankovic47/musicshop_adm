package com.musicshop.customer;

import com.musicshop.rest.GenericDao;

public interface CustomerDao extends GenericDao<Customer, String>{

	void create(Customer customer);
	void active(String email, boolean active);
}
