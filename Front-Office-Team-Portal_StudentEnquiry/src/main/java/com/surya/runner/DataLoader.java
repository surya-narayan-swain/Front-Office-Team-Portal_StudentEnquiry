package com.surya.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.surya.entity.CoursesEntity;
import com.surya.entity.EnquiryStatusEntity;
import com.surya.repository.CoursesRepositary;
import com.surya.repository.EnquiryStatusRepositary;

@Component
public class DataLoader implements CommandLineRunner{

	@Autowired
	private CoursesRepositary courseRepo;
	
	@Autowired
	private EnquiryStatusRepositary statusRepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	courseRepo.deleteAll();
		
		CoursesEntity entity1=new CoursesEntity();
	     entity1.setCourseName("JAVA");
	     CoursesEntity entity2=new CoursesEntity();
	    entity2.setCourseName("Devops");
	     CoursesEntity entity3=new CoursesEntity();
	    entity3.setCourseName("AWS");
	    System.out.println(courseRepo.saveAll(Arrays.asList(entity1,entity2,entity3)));
	    courseRepo.saveAll(Arrays.asList(entity1,entity2,entity3));
	    
	    
	    
	   statusRepo.deleteAll();
	    EnquiryStatusEntity e1=new EnquiryStatusEntity();
	    e1.setEnquiryStatus("New");
	    EnquiryStatusEntity e2=new EnquiryStatusEntity();
	    e2.setEnquiryStatus("Enrolled");
	    System.out.println(e1);
	    EnquiryStatusEntity e3=new EnquiryStatusEntity();
	    e3.setEnquiryStatus("Lost");
	    statusRepo.saveAll(Arrays.asList(e1,e2,e3));
	    
	}
	
	
	

}
