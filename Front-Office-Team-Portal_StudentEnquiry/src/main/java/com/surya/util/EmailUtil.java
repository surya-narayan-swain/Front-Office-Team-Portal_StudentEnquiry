package com.surya.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
    
	@Autowired
	private JavaMailSender mailsender;
	
	public boolean mailSender(String to,String body,String subject) throws Exception{
		boolean issent=false;
		
			MimeMessage mimeMessage = mailsender.createMimeMessage();
		   
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			
			mailsender.send(mimeMessage);
			
			issent=true;
		
		
		return issent;
		
		
		
	}
}
