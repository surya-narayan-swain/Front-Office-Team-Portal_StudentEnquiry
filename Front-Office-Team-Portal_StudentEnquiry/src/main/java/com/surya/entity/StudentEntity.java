package com.surya.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer enqId;

		private String studentName;
		private String studentPhn;
		private String classStatus;
		private String courseName;
		private String enquiryStatus;
		
		@CreationTimestamp
		private LocalDate dateCreated;
		
		@UpdateTimestamp
		private LocalDate updateDate; 
		
		@ManyToOne
		@JoinColumn(name = "userId",referencedColumnName="userId")
	    private UserDtlsEntity userDtlsEntity;

		@Override
		public String toString() {
			return "StudentEntity [enqId=" + enqId + ", studentName=" + studentName + ", studentPhn=" + studentPhn
					+ ", classStatus=" + classStatus + ", courseName=" + courseName + ", enquiryStatus=" + enquiryStatus
					+ ", dateCreated=" + dateCreated + ", updateDate=" + updateDate + ", userDtlsEntity.userId="
					+ userDtlsEntity.getUserId() + "]";
		}
		
		
		
	}

	
	
	
	

