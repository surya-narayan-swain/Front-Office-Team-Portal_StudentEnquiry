package com.surya.service;

import java.util.List;

import com.surya.binding.DashBoardResponse;
import com.surya.binding.EnquiryForm;
import com.surya.binding.EnquirySearchCriteria;
import com.surya.entity.StudentEntity;

public interface IEnquiryService {
	
	 DashBoardResponse getDashBoardResponse(Integer userId);

	 //dropdown in addEnquiry
	  List<String> getCourses();
		 //dropdown in addEnquiry
	 List<String> enquiryStatus();
	 
	 public boolean saveEnqForm(EnquiryForm form);
	 
	 public List<StudentEntity> getEnquries();
	 
	 public List<StudentEntity> getFilterData(EnquirySearchCriteria criteria,Integer userId);
}
