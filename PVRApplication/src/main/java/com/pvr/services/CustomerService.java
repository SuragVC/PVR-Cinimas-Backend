package com.pvr.services;
//S3
import com.pvr.entity.Customer;
import com.pvr.exceptions.CustomerException;
import com.pvr.payloads.Message;



public interface CustomerService {
	
	public Customer registerCustomer(Customer customer) throws CustomerException;
	
	public Message updateCustomer(Customer customer) throws CustomerException;
	
	public Message deleteCustomer(String username,String password) throws CustomerException;
	
	public Message loginCustomer(String username,String password) throws CustomerException;

	
}
