package com.example.demo.serviceImpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.service.EmailSenderService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String toEmail, String subject, String body) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("onboarding.fdaindia@gmail.com");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(body, true);

			// Add the logo image
//			FileSystemResource res = new FileSystemResource(new File("C:\\Users\\LENOVO\\Downloads\\Fdalogo.png"));
			FileSystemResource res = new FileSystemResource(new File("C:\\Users\\hp\\Music\\fdalogo.jpeg"));
			helper.addInline("logoImage", res);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
