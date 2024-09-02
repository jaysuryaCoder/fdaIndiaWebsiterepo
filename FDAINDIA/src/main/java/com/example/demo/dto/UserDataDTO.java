package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDataDTO {
	private Long userId;
	private String name;
	private String email;
	private String username;
	private String mobile;
	private String state;
	private Long token;
	private Double totalAmount;
	private Double deposit;
	private Double withdrwalAmount;
	private Double transaction;
	private Double borrowMoney;
}
