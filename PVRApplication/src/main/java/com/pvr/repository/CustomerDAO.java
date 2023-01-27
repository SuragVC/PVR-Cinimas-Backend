package com.pvr.repository;
//R2
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.Customer;


public interface CustomerDAO extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByEmail(String email);
	
	Optional<Customer>  findByMobileNo(String mobileNo);
}
