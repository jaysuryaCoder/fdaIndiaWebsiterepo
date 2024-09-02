package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.response.AdminRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.response.VerifyOtpRequest;
import com.example.demo.service.AdminService;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.OTPService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@Autowired
	private OTPService otpService;

	@Autowired
	private EmailSenderService emailSenderService;

	@CrossOrigin()
	@PostMapping("/signup")
	public UserResponse signUp(@RequestBody Admin admin) {
		UserResponse response = new UserResponse();
		Admin ad = service.signUp(admin);
		if (ad != null) {
			response.setStatus(true);
			response.setObject(ad);
			String body = "Dear Admin\n" + "Your UserName: " + ad.getUsername() + "\n" + "Your Password: "
					+ ad.getPassword();

			String subject = "SignUp for Admin";
			emailSenderService.sendEmail(ad.getEmail(), subject, body);
			response.setMessage("Admin SignUp Created successfully");
		} else {
			response.setStatus(false);
			response.setObject(null);
			response.setMessage("Admin SignUp not Created successfully");
		}
		return response;
	}

	@CrossOrigin()
	@PostMapping("/login")
	public UserResponse login(@RequestBody AdminRequest adminRequest) {
		UserResponse response = new UserResponse();
		Admin admin = service.login(adminRequest.getUsernameOrEmail(), adminRequest.getPassword());
		if (admin != null) {
			response.setStatus(true);
			String subject = "Email otp Verification ";
			String body = "Your Otp is : " + otpService.generateOTP(admin.getEmail());
			emailSenderService.sendEmail(admin.getEmail(), subject, body);
			response.setObject(admin);
			response.setMessage("Login SuccessFull");
		} else {
			response.setStatus(false);
			response.setMessage("Invalid Credentials");
		}
		return response;
	}
	
//	   @CrossOrigin()
//	    @PostMapping("/verifyotp")
//	    public String verifyEmail(@RequestBody OTPRequest otpRequest) {
//		   
//	        boolean isVerified = otpService.verifyOTP(otpRequest.getEmail(), otpRequest.getOtp());
//	        
//	        if (isVerified) {
//	            return "Email verified successfully";
//	        } else {
//	            return "Invalid OTP";
//	        }
//	    }
	
	 @PostMapping("/verifyotp")
	    public String verifyEmail(@RequestBody VerifyOtpRequest otpRequest) {
	        boolean isVerified = false;

	        if (otpRequest.getEmail() != null && !otpRequest.getEmail().isEmpty()) {
	            isVerified = otpService.verifyOTP(otpRequest.getEmail(), otpRequest.getOtp());
	        } else if (otpRequest.getUsername() != null && !otpRequest.getUsername().isEmpty()) {
	            String email = service.getEmailByUsername(otpRequest.getUsername());
	            if (email != null) {
	                isVerified = otpService.verifyOTP(email, otpRequest.getOtp());
	            }
	        }

	        if (isVerified) {
	            return "Email verified successfully";
	        } else {
	            return "Invalid OTP";
	        }
	    }

}
