package com.example.demo.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.CardKycEntity;

public interface UserKycService {
//	 public CardKycEntity saveUserKyc(String name, String dob,String pancardNumber, String adharNumber, String mobile, String email, String gender,
//             String fatherName, String maritalStatus, String spouseName,
//             String ownerAddress, String kendrarea,
//             String state, String district, String pincode, String bloodgroup,
//             String qualification) throws IOException;
//
//	  public CardKycEntity getUserKyc(Long id) throws IOException;
//	  
//	  public String saveFile(MultipartFile file) throws IOException;
	
	public CardKycEntity saveUser(CardKycEntity user, MultipartFile adharCard, MultipartFile panCard, MultipartFile domicile, MultipartFile idProff) throws IOException;

}
