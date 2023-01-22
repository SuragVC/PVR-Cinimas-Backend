package com.pvr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments implements Serializable{
	
	private static final long serialVersionUID = -6127764876287433846L;
	@Id
	private Long paymentID;
	@NotNull
	private Double totalPayment;
	private Double returnAmount;
}
