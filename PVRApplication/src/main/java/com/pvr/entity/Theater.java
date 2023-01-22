package com.pvr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
	@Id
	private Long theaterID;
	private String name;
	@OneToOne
	private Address address;
	private String runningMovie;
	private Integer seats;
	private Integer availableSeats;
	private Double priceForSeat;
}
