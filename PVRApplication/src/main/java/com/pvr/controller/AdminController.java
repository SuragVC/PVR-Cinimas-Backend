package com.pvr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.config.SecurityUser;
import com.pvr.config.jwt.JwtUtils;
import com.pvr.entity.Admin;
import com.pvr.entity.Customer;
import com.pvr.exceptions.AdminException;
import com.pvr.exceptions.CustomerException;
import com.pvr.payloads.JwtResponse;
import com.pvr.payloads.LoginRequest;
import com.pvr.payloads.Message;
import com.pvr.services.AdminService;
import com.pvr.util.TwilioSMSService;

@RestController
@RequestMapping("pvr")
public class AdminController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private AdminService adminService;
	
	@CrossOrigin
	@PostMapping("/adminUser/register")
	public ResponseEntity<Message> Admin_Signup_Handler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Message msg = adminService.registerAdmin(admin);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
		
	}
	
	  @CrossOrigin
	  @PostMapping("/adminUser/login")
	  public ResponseEntity<?> Admin_Login_Handler(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

	    return ResponseEntity.ok(new JwtResponse(jwt,
					    		"Bearer",
					    		userDetails.getUsername(),  
	                         	"ADMIN"));
	  }

	@CrossOrigin
	@PostMapping("/admin/update")
	public ResponseEntity<Message>Admin_Update_Handler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Message msg = adminService.updateAdmin(admin);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	  
	@CrossOrigin
	@DeleteMapping("/admin/delete/{user}/{password}")
	public ResponseEntity<Message> Admin_Delete_Handler (@PathVariable String user ,@PathVariable String password) throws AdminException{
		
		Message msg = adminService.deleteAdmin(user, password);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	
	

	
	@CrossOrigin
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> Get_All_Customers () throws CustomerException{
		
		List<Customer> customers = adminService.getAllCustomers();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/customers/{id}")
	public ResponseEntity<Customer> Find_Customer_By_ID (@PathVariable Long id) throws CustomerException{
		
		Customer customer = adminService.getCustomerDetailsById(id);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	


}

