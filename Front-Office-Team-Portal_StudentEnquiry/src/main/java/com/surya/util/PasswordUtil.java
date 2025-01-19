package com.surya.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	public static String genRandomPwd() {
		String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
		String pwd=RandomStringUtils.random(6,characters);
		return pwd;
		
		
	}

}
