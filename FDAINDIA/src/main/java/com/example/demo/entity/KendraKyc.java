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
@Table(name="kendrakyc_tbl")
@Data
public class KendraKyc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String dob;
	@Column(nullable = false)
	private String adharNumber;
	@Column(nullable = false)
	private String pancardNumber;
	@Column(nullable = false)
	private String mobile;
	@Column(unique = true)
	private String email;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false)
	private String fatherName;
	@Column(nullable = false)
	private String motherName;
	@Column(nullable = false)
	private String maritalStatus;
	@Column(nullable = true)
	private String spouseName;
	@Column(nullable = false)
	private String ownerAddress;
	@Column(nullable = false)
	private String addressLine1;
	@Column(nullable = true)
	private String addressLine2;
	@Column(nullable = false)
	private String kendrarea;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String district;
	@Column(nullable = false)
	private String pincode;
	@Column(nullable = false)
	private String bloodgroup;
	@Column(nullable = true)
	private String medicalHistory;
	@Column(nullable = false)
	private String qualification;
	@Column(nullable = true)
	private String transactionNumber;
	@Column(nullable = false)
	private String village;
	@Column(nullable = false)
	private String block;

	private String adharCardPath;
	private String panCardPath; 
    private String domicilePath;
    private String lpcPath;
    private String idProofPath;
    
    private String screenShotPath;
    
    
    

	
}
