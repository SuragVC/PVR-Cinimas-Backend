package com.pvr.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.config.SecurityUser;
import com.pvr.config.jwt.JwtUtils;
import com.pvr.entity.Customer;
import com.pvr.exceptions.CustomerException;
import com.pvr.payloads.JwtResponse;
import com.pvr.payloads.LoginRequest;
import com.pvr.payloads.Message;
import com.pvr.services.CustomerService;

@RestController
@RequestMapping("/pvr")
public class CustomerController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private CustomerService customerService;

	@CrossOrigin
	@PostMapping("/customer/register")
	public ResponseEntity<Customer> Customer_Signup_Handler(@Valid @RequestBody Customer customer)
			throws CustomerException {

		Customer cust = customerService.registerCustomer(customer);

		return new ResponseEntity<Customer>(cust, HttpStatus.ACCEPTED);
	}

	@CrossOrigin
	@PostMapping("/customer/update")
	public ResponseEntity<Message> Customer_Update_Handler(@Valid @RequestBody Customer customer)
			throws CustomerException {

		Message msg = customerService.updateCustomer(customer);

		return new ResponseEntity<Message>(msg, HttpStatus.ACCEPTED);
	}

	@CrossOrigin
	@DeleteMapping("/customer/delete/{user}/{password}")
	public ResponseEntity<Message> Customer_Delete_Handler(@PathVariable String user, @PathVariable String password)
			throws CustomerException {

		Message msg = customerService.deleteCustomer(user, password);

		return new ResponseEntity<Message>(msg, HttpStatus.ACCEPTED);
	}

	@CrossOrigin
	@PostMapping("/customer/login")
	public ResponseEntity<?> Customer_Login_Handler(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), "USER"));
	}

}