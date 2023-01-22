package com.pvr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicket implements Serializable{

	private static final long serialVersionUID = -521762267547817859L;
	@Id
	private Long movieTicketID;
	@NotNull
	@NotBlank
	private String theaterName;
	
	private Integer ticketCount;
	@OneToOne
	private Payments payment;
	private String status;
}
