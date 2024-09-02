package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.TransactionForKendra;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @CrossOrigin()
    @PostMapping
    public TransactionForKendra createTransaction(@RequestBody TransactionForKendra transaction) {
    	System.out.println("Hit the mapping"+transaction);
        return transactionService.createTransaction(transaction);
    }

    @CrossOrigin()
    @GetMapping("/count/{userId}")
    public long countTransactionsByUser(@PathVariable Long userId) {
        return transactionService.countTransactionsByUser(userId);
    }
    
    @CrossOrigin()
    @PostMapping("/getAllKendraTransaction/{userId}")
    public List<TransactionDto> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
    
}
