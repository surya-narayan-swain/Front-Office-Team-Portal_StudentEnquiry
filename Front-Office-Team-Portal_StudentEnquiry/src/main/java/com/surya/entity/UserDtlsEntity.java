package com.surya.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class UserDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String name;
	
	private String pwd;
	
	private String email;
	
	private Long phn;
	
	private String accStatus;
	

    @OneToMany(mappedBy = "userDtlsEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<StudentEntity> studentEntity;


	@Override
	public String toString() {
		return "UserDtlsEntity [userId=" + userId + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", phn="
				+ phn + ", accStatus=" + accStatus + ", studentEntity=" + studentEntity + "]";
	} 
	
    
}
