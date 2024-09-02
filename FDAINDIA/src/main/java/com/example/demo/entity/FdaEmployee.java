package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="fda_employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FdaEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phoneNumber;
	private String alternatePhoneNumber;
	private String email;
	private String refferedBy;
	private String bloodGroup;
	private String fatherName;
	private String motherName;
	private String empCode;
	private String relativeNumber;
	private String permanentAddress;
	private String adharCard;
	private String temporaryAddress;
	private String panCard;
	private String password;
	private String location;
	private String designation;
	private String photoPath;
	
	
}
