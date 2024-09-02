package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Random;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_kendra")
public class TransactionForKendra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @Column()
    private double depositAmount;

    @Column()
    private double borrowAmount;

    @Column()
    private double totalAmount;

    @Column()
    private double discount;

    @Column()
    private double specialAllowance;

    @Column()
    private double withdraw;

    @Column(unique = true)
    private String transactionId;

    @Column(unique = true)
    private String receiptNumber;
    
    private String localDate;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private KendraUser user;
    
    

    @PrePersist
    public void generateTransactionIdAndReceiptNumber() {
    	LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        String formattedDate = myDateObj.format(myFormatObj);
        String randomDigits = String.format("%06d", new Random().nextInt(1000000));
        this.transactionId = "TRX" + dateTime + randomDigits;
        this.receiptNumber = "REC" + dateTime + randomDigits;
        this.localDate=formattedDate;
    }
}
