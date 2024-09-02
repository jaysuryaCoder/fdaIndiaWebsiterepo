package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="cardkyctable")
public class CardKycEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String dob;
	private String adharNumber;
	private String pancardNumber;
	private String mobile;
	
	@Column(nullable = false,unique = true)
	private String email;
	private String gender;;
	private String fatherName;
	private String maritalStatus;
	private String spouseName;
	private String kendrarea;
	private String state;
	private String district;
	private String block;
	private String village;
	private String pincode;
	private String bloodgroup;
	private String qualification;
	
	private String adharCard;
	private String panCard;
	private String domicile;
	private String idProff;	
	

}