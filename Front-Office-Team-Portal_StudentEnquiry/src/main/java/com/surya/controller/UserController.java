package com.surya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.surya.binding.LoginForm;
import com.surya.binding.SignUpForm;
import com.surya.binding.UnLockForm;
import com.surya.repository.UserDtlsRepositary;
import com.surya.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserDtlsRepositary userRepo;

	@PostMapping("/signup")
	public String handlerSignUp(SignUpForm signUpForm, Model model) {

		boolean status;
		try {
			status = userService.signUp(signUpForm);
			if (status) {
				model.addAttribute("success", "Signup Sussessful\nCheck your mail");
			} else {
				model.addAttribute("error", "Account already available with this Email :: " + signUpForm.getEmail());
			}
		} catch (Exception e) {
			System.out.println("sign up failed");
		}
		model.addAttribute("user", signUpForm);

		return "signup";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	

	@GetMapping("/unlock")
	public String unlock(@RequestParam String email, Model model) {
		UnLockForm unlockFormobj = new UnLockForm();
		unlockFormobj.setEmail(email);
		model.addAttribute("unlock", unlockFormobj);
		System.out.println(email);
		return "unlock";
	}

	@PostMapping("/unlock")
	public String processUnlock(Model model, @ModelAttribute("unlock") UnLockForm unlockForm) {
		// System.out.println("email "+email+" eontroller");
		System.out.println("form data" + unlockForm);
		String msg = userService.unlock(unlockForm);
			model.addAttribute("msg", msg);
		return "unlock";
	}

	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginform", loginForm);

		return "login";
	}

	@PostMapping("/login")
	public String loginForm(Model model, @ModelAttribute("loginform") LoginForm loginForm) {
		System.out.println(loginForm);

		String status = userService.login(loginForm);

		if (status.contains("success")) {
			httpSession.setAttribute("userId",userRepo.findByEmail(loginForm.getEmail()).getUserId());
			return "redirect:dashboard";
		}
		model.addAttribute("error", status);

		return "login";
	}

	
	@GetMapping("/forgot")
	public String forgotpwd() {
		return "forgot";
	}
	
	@PostMapping("/forgot")
	public String forgotpwd(@RequestParam("email")String email,Model model) {
		System.out.println(email);
		
		boolean status = userService.forgetPwd(email);
		
		if(status)
			model.addAttribute("success","password sent to your mail");
		else
			model.addAttribute("error" ,"some error occured");
		return "forgot";
	}
	
	
	@GetMapping("/logout")
	public String logOut() {
		httpSession.invalidate();
		return "logout";
	}
	
	
	
	
	
	
	
	
	
	
}
