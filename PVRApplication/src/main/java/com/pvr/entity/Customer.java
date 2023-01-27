package com.pvr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotNull
	@NotBlank
	@Size(min=10,max=10,message="Mobile no must be 10 digits!")
	@Pattern(regexp = "\\d+", message="Mobile number should be digits only!")
	private String mobileNo;
	
	private String role = "USER";


}
