package com.pvr.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pvr.entity.Admin;
import com.pvr.entity.Customer;
import com.pvr.repository.AdminDAO;
import com.pvr.repository.CustomerDAO;

@Service
public class CustomUser implements UserDetailsService{
	
	@Autowired
	private AdminDAO adminDao;
	
	@Autowired
	private CustomerDAO customerDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> opt = customerDao.findByEmail(username);
		
		if(opt.isEmpty()) {
			
			Optional<Admin> opt2  = adminDao.findByEmail(username);
			
			if(opt2.isEmpty()) throw new UsernameNotFoundException("User Does not exist");
			
			return new SecurityUser(opt2.get());
		}
		
		return new SecurityUser(opt.get());
	}

}
