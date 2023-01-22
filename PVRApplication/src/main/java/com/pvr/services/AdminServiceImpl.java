package com.pvr.services;
//S2
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvr.entity.Admin;
import com.pvr.entity.Customer;
import com.pvr.exceptions.AdminException;
import com.pvr.exceptions.CustomerException;
import com.pvr.payloads.Message;
import com.pvr.repository.AdminDAO;
import com.pvr.repository.CustomerDAO;

@Service
public class AdminServiceImpl implements AdminService {
	private AdminDAO adminDao;
	private CustomerDAO customerDao;
	private PasswordEncoder encoder;
	
	@Autowired
	public  AdminServiceImpl(PasswordEncoder encoder,CustomerDAO customerDao,AdminDAO adminDao) {
		this.encoder=encoder;
		this.customerDao=customerDao;
		this.adminDao=adminDao;
	}
	//S2.1
	@Override
	public Message registerAdmin(Admin admin) throws AdminException {

		if (admin == null)
			throw new AdminException("Enter Valid Admin Details..!");
		Optional<Admin> adminOpt=adminDao.findByEmail(admin.getEmail());
		if(adminOpt.isPresent())
			throw new AdminException("Account already registred with this email! Try another");
		admin.setPassword(encoder.encode(admin.getPassword()));
		admin.setRole("ADMIN");
		adminDao.save(admin);

		return new Message("Admin registered with Email :- " + admin.getEmail());
	}
	//S2.2
	@Override
	public Message loginAdmin(String email, String password) throws AdminException {
		
		Optional<Admin> opt = adminDao.findByEmail(email);

		if (opt.isEmpty())
			throw new AdminException("Enter Valid Admin Email..!");

		if (opt.get().getPassword().equals(password) == false)
			throw new AdminException("Enter Valid Admin Password..!");

		return new Message("Login Sucessfully with AdminId " + opt.get().getAdminId());
	}
	//S2.3
	@Override
	public Message deleteAdmin(String email, String password) throws AdminException {
		
		Optional<Admin> opt = adminDao.findByEmail(email);

		if (opt.isEmpty())
			throw new AdminException("Enter Valid Admin Email..!");

		if (opt.get().getPassword().equals(password) == false)
			throw new AdminException("Enter Valid Admin Password..!");

		adminDao.deleteById(opt.get().getAdminId());

		return new Message("Delete Sucessfully with Id " + opt.get().getAdminId());
	}
	//S2.4
	@Override
	public Message updateAdmin(Admin admin) throws AdminException {

		Optional<Admin> opt = adminDao.findById(admin.getAdminId());

		if (opt.isEmpty())
			throw new AdminException("Invalid Admin details");

		adminDao.save(admin);

		return new Message("Updated Sucessfully with Id " + admin.getAdminId());

	}
	//S3.5
	@Override
	public List<Customer> getAllCustomers() throws CustomerException {

		List<Customer> customers = customerDao.findAll();

		if (customers.size() == 0)
			throw new CustomerException("NO Customer Available...!");

		return customers;
	}
	//S3.6
	@Override
	public Customer getCustomerDetailsById(Long id) throws CustomerException {

		Optional<Customer> customer = customerDao.findById(id);

		if (customer.isEmpty())
			throw new CustomerException("Wrong customer id");

		return customer.get();
	}

}
