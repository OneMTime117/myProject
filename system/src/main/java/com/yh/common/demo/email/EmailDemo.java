package com.yh.common.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailDemo {

	@Autowired
	private JavaMailSenderImpl mailSender;

	public void sendSimpleMail(String subject, String text, String to) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject("TEST");
		simpleMailMessage.setText("今天星期几");
		simpleMailMessage.setTo("yuanhuanpeipei@gmail.com");
		simpleMailMessage.setFrom(mailSender.getUsername());
		mailSender.send(simpleMailMessage);
	}

	public void sendMimeMail(String subject, String text, String to, String attachmentName, File file) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("TEST");
		mimeMessageHelper.setText("今天星期几");
		mimeMessageHelper.setTo("yuanhuanpeipei@gmail.com");
		mimeMessageHelper.setFrom(mailSender.getUsername());
		if (file!=null){
			mimeMessageHelper.addAttachment(attachmentName, file);
		}
		mailSender.send(mimeMessage);
	}
}
