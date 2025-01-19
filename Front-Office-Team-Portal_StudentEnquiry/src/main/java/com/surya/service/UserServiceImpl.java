package com.surya.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surya.binding.LoginForm;
import com.surya.binding.SignUpForm;
import com.surya.binding.UnLockForm;
import com.surya.entity.UserDtlsEntity;
import com.surya.repository.UserDtlsRepositary;
import com.surya.util.EmailUtil;
import com.surya.util.PasswordUtil;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDtlsRepositary userDtlsRepo;

	@Autowired
	private EmailUtil emailutil;

	@Autowired
	private HttpSession httpSession;

	@Override // binding object
	public boolean signUp(SignUpForm signUpForm) throws Exception {
		UserDtlsEntity byEmail = userDtlsRepo.findByEmail(signUpForm.getEmail());
		if (byEmail != null)
			return false;
		// copy data to entity object from binding object
		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(signUpForm, entity);

		// generate pwd
		String temPwd = PasswordUtil.genRandomPwd();
		entity.setPwd(temPwd);

		// set acc status has locked
		entity.setAccStatus("locked");

		String to = signUpForm.getEmail();
		String subject = "Unlock your Account";

		StringBuffer body = new StringBuffer();
		body.append("<h1>Use below Temp Pwd</h1>");
		body.append("Temp Pwd::" + temPwd);
		body.append("<br>");
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">click here to unlock your account</a>");
		// sent email to unlock
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("before email");
					emailutil.mailSender(to, body.toString(), subject);
					System.out.println("after email");
					// insert record
					userDtlsRepo.save(entity);
				} catch (Exception e) {
					System.out.println("mail not sent or data not inserted " + signUpForm);
				}
			}
		});
		thread.start();
		return true;
	}

	@Override
	public String unlock(UnLockForm unlockForm) {
		UserDtlsEntity user = userDtlsRepo.findByEmail(unlockForm.getEmail());
		// System.out.println("email "+email);
		if (!user.getPwd().equalsIgnoreCase(unlockForm.getTempPwd())) {
			System.out.println(user.getPwd() + "  " + unlockForm.getTempPwd());
			return "Incorrect Temporary Password";
		} else if (!unlockForm.getConfirmPwd().equalsIgnoreCase(unlockForm.getNewPwd()))
			return "Mismatch Password";
		else if (user.getAccStatus().equalsIgnoreCase("UnLocked"))
			return "Already Unlocked";
		user.setPwd(unlockForm.getConfirmPwd());
		user.setAccStatus("UnLocked");
		userDtlsRepo.save(user);
		return "Unlock Success";

	}

	@Override
	public String login(LoginForm loginForm) {
		UserDtlsEntity entity = userDtlsRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());
		if (entity == null) {
			return "Invalid Creditianls";
		}
		if (entity.getAccStatus().equals("Locked")) {
			return "Your Account Locked";
		}
		return "success";

	}

	@Override
	public boolean forgetPwd(String email) {

		UserDtlsEntity entity = userDtlsRepo.findByEmail(email);

		if (entity == null)
			return false;

		String subject = "Recover your password";
		String body = " password is::" + entity.getPwd();
		try {
			emailutil.mailSender(email, body, subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//httpSession.setAttribute("userId",userDtlsRepo.getUserId());


		return true;
	}

}