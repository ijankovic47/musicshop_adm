package com.musicshop.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.musicshop.customer.Customer;
import com.musicshop.customer.CustomerDao;

@Component
public class DbUserDetailsService implements UserDetailsService {

	private CustomerDao customerDao;

	@Autowired
	public DbUserDetailsService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer c=customerDao.readById(email);
		if(c.getEmail().equals("admin")) {
			List<GrantedAuthority> authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User(c.getEmail(),c.getPassword(), authorities);
		}
		return c;
	}

}
