package com.surya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.surya.binding.DashBoardResponse;
import com.surya.binding.EnquiryForm;
import com.surya.binding.EnquirySearchCriteria;
import com.surya.entity.StudentEntity;
import com.surya.service.IEnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private IEnquiryService enquiryService;

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		Integer userid = (Integer) httpSession.getAttribute("userId");

		DashBoardResponse dashBoard = enquiryService.getDashBoardResponse(userid);

		model.addAttribute("dashBoard", dashBoard);

		return "dashboard";
	}

	@GetMapping("/addenquiry")
	public String addEnquiry(Model model) {

		List<String> courses = initForm(model);
		System.out.println(courses);
		EnquiryForm form = new EnquiryForm();
		model.addAttribute("form", form);

		return "addEnquiry";
	}

	private List<String> initForm(Model model) {
		List<String> courses = enquiryService.getCourses();
		List<String> status = enquiryService.enquiryStatus();

		model.addAttribute("courses", courses);
		model.addAttribute("status", status);
		return courses;
	}

	@PostMapping("/addEnquiry")
	public String addEnquiry(@ModelAttribute("form") EnquiryForm enquiryForm, Model model) {
		System.out.println(enquiryForm);
		boolean status = enquiryService.saveEnqForm(enquiryForm);
		if (status)
			model.addAttribute("success", "Enquiry Added successfully");
		else
			model.addAttribute("error", "Some problem occured");
		return "/addEnquiry";
	}

	@GetMapping("/view")
	public String viewEnquiry(Model model) {
		initForm(model);
		model.addAttribute("filter", new EnquirySearchCriteria());
		List<StudentEntity> enquries = enquiryService.getEnquries();
		model.addAttribute("enquries", enquries);
		return "view";
	}

	@PostMapping("/filter")
	public String searchFilter(Model model, @ModelAttribute EnquirySearchCriteria filter) {
		System.out.println(filter);
		initForm(model);
		return "view";
	}

	@GetMapping("/filter")
	public String getFilterEnq(@RequestParam String course,
	@RequestParam String status,
	@RequestParam String mode,Model model) {
		
		EnquirySearchCriteria criteria=new EnquirySearchCriteria();
		criteria.setCourseName(course);
		criteria.setClassMode(mode);
		criteria.setEnquiryStatus(status);
		System.out.println(criteria+"heloooooo");
		
		Integer userid = (Integer) httpSession.getAttribute("userId");
        
		List<StudentEntity> filterData = enquiryService.getFilterData(criteria, userid);
		
		model.addAttribute("enquries",filterData);
		
		return "filterSearch";
	}
	
	
	@GetMapping("/edit")
	public String getMethodName(Integer id,@ModelAttribute("form") EnquiryForm enquiryForm) {
		System.out.println(id);
		return "addEnquiry";
	}

}
