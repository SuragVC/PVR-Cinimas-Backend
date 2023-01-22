package com.pvr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//E3
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements UserInterface{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@NotNull
	private String customerName;
	
	@NotNull
	private String password;
	
	@NotNull
	@Email
	private String email;
	
	private String role = "USER";


}
