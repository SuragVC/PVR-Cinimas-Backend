package com.pvr.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//E4
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movieID;
	@NotNull
	@NotBlank
	private String name;
	private LocalDate releaseDate;
	@NotNull
	@NotBlank
	private String language;
	@NotNull
	@NotBlank
	private String Genere;
	private String info;
	//E4.1
	@OneToOne
	private Cast cast;
}
