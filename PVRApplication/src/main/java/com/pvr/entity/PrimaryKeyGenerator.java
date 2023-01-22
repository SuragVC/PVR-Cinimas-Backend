package com.pvr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="KeyAutoGenerator")
public class PrimaryKeyGenerator {
	@Id
	private Long keyID;
	private Long nextValue;
}
