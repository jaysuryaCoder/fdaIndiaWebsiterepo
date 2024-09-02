package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private Long tid;
	private double depositAmount;
	private double borrowAmount;
	private double totalAmount;
	private double discount;
	private double specialAllowance;
	private double withdraw;
	private String transactionId;
	private String receiptNumber;
}