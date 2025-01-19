package com.surya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class EnquiryStatusEntity {

	@Id
	@GeneratedValue
	private Integer id; 
	
	private String enquiryStatus;

	
	
}
