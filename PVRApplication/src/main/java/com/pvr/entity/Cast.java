package com.pvr.entity;
//E5
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cast {
	
	@Id
	private Long castId;
	@NotNull
	private String actorName;
	@NotNull
	private String actorImageLink;
	@NotNull
	@NotBlank
	private String actressName;
	@NotNull
	@NotBlank
	private String actressImageLink;
	
}
