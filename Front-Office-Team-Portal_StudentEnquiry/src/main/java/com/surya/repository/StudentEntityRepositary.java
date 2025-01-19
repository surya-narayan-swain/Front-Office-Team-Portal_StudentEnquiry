package com.surya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surya.entity.StudentEntity;

public interface StudentEntityRepositary extends JpaRepository<StudentEntity, Integer>{
	
	

}
