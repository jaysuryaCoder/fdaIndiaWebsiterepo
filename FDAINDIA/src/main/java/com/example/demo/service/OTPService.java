package com.example.demo.service;

public interface OTPService {

	public boolean verifyOTP(String email, int otp);

	public int generateOTP(String email);

	public Long generateOTPLong(String email);
}
