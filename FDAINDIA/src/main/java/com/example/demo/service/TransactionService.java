package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.TransactionForKendra;

public interface TransactionService {
	
	public TransactionForKendra createTransaction(TransactionForKendra transaction);
	public long countTransactionsByUser(Long userId);
	public List<TransactionForKendra> getAllTransactionsByUserId(Long userId);
	public List<TransactionDto> getTransactionsByUserId(Long userId);
	
}
