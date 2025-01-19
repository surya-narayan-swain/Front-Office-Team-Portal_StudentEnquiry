package com.surya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CoursesEntity {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String courseName;



}
