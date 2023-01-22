package com.pvr.services;
//S1
import java.util.List;

import com.pvr.entity.Admin;
import com.pvr.entity.Customer;
import com.pvr.exceptions.AdminException;
import com.pvr.exceptions.CustomerException;
import com.pvr.payloads.Message;



public interface AdminService {
	
	public Message registerAdmin(Admin admin) throws AdminException;
	
	public Message loginAdmin(String username,String password) throws AdminException;
	
	public Message deleteAdmin(String username,String password) throws AdminException;
	
	public Message updateAdmin(Admin admin) throws AdminException;
	
	public List<Customer> getAllCustomers() throws CustomerException;
	
	public Customer getCustomerDetailsById(Long userId) throws CustomerException;

}
