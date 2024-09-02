package com.example.demo.response;

import lombok.Data;

@Data
public class OTPRequest {
	private String email;
	private int otp;
}
