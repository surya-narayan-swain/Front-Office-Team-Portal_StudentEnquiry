package com.surya.service;

import com.surya.binding.LoginForm;
import com.surya.binding.SignUpForm;
import com.surya.binding.UnLockForm;

public interface IUserService {
	
	public boolean signUp(SignUpForm signUpForm) throws Exception;
	public String unlock(UnLockForm unlockForm);
	public String login(LoginForm loginForm);
	public boolean forgetPwd(String email);
	//boolean unlock(UnLockForm unlockForm);
}
