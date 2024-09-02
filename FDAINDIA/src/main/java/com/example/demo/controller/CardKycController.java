package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CardKycEntity;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserKycService;

@RestController
@RequestMapping("/api/cardkyc")
public class CardKycController {

	@Autowired
	private UserKycService userService;

	@CrossOrigin()
	@PostMapping("/create")
	public UserResponse createUser(@RequestParam("name") String name, @RequestParam("dob") String dob,
			@RequestParam("village") String village, @RequestParam("adharNumber") String adharNumber,
			@RequestParam("pancardNumber") String pancardNumber, @RequestParam("mobile") String mobile,
			@RequestParam("email") String email, @RequestParam("gender") String gender,
			@RequestParam("fatherName") String fatherName, @RequestParam("maritalStatus") String maritalStatus,
			@RequestParam("spouseName") String spouseName, @RequestParam("kendrarea") String kendrarea,
			@RequestParam("state") String state, @RequestParam("district") String district,@RequestParam("block") String block,
			@RequestParam("pincode") String pincode, @RequestParam("bloodgroup") String bloodgroup,
			@RequestParam("qualification") String qualification,
			@RequestParam("adharCard") MultipartFile adharCard,
			@RequestParam("panCard") MultipartFile panCard,
			@RequestParam("domicile") MultipartFile domicile,
			@RequestParam("idProff") MultipartFile idProff) throws Exception {
		CardKycEntity user = new CardKycEntity();
		UserResponse response = new UserResponse();
		user.setName(name);
		user.setAdharNumber(adharNumber);
		user.setBloodgroup(bloodgroup);
		user.setDistrict(district);
		user.setDob(dob);
		user.setEmail(email);
		user.setFatherName(fatherName);
		user.setMaritalStatus(maritalStatus);
		user.setGender(gender);
		user.setMobile(mobile);
		user.setKendrarea(kendrarea);
		user.setPincode(pincode);
		user.setSpouseName(spouseName);
		user.setState(state);
		user.setQualification(qualification);
		user.setPancardNumber(pancardNumber);
		user.setBlock(block);
		user.setVillage(village);
		userService.saveUser(user, adharCard, panCard, domicile, idProff);
		response.setStatus(true);
		response.setObject(user);
		response.setMessage("Kyc Detail Updated successFully ");
		System.out.println(response);
		return response;
	}

}