package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.KendraUser;
import com.example.demo.entity.KendraKyc;
import com.example.demo.response.UserResponse;
import com.example.demo.service.KendraKycService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/kyckendra")
public class KendraKycController {

	@Autowired
	private KendraKycService kendraKycService;
	
	@Autowired
	private UserService us;
	
	private static String femail;
	private static String fstate;
	private static String dist;
	private static String fvillage;

	private final String storageDirectory = "C:\\Fda\\KYCData\\KendraKycDocument\\";

	@CrossOrigin()
	@PostMapping("/create")
	public UserResponse createUser(@RequestParam("name") String name, @RequestParam("dob") String dob,
			@RequestParam("transactionNumber") String transactionNumber, @RequestParam("village") String village,
			@RequestParam("block") String block, @RequestParam("adharNumber") String adharNumber,
			@RequestParam("pancardNumber") String pancardNumber, @RequestParam("mobile") String mobile,
			@RequestParam("email") String email, @RequestParam("gender") String gender,
			@RequestParam("fatherName") String fatherName, @RequestParam("motherName") String motherName,
			@RequestParam("maritalStatus") String maritalStatus, @RequestParam("spouseName") String spouseName,
			@RequestParam("ownerAddress") String ownerAddress, @RequestParam("addressLine1") String addressLine1,
			@RequestParam("addressLine2") String addressLine2, @RequestParam("kendrarea") String kendrarea,
			@RequestParam("state") String state, @RequestParam("district") String district,
			@RequestParam("pincode") String pincode, @RequestParam("bloodgroup") String bloodgroup,
			@RequestParam("medicalHistory") String medicalHistory, @RequestParam("qualification") String qualification,
			@RequestParam("adharCard") MultipartFile adharCard, @RequestParam("panCard") MultipartFile panCard,
			@RequestParam("lpc") MultipartFile lpc, @RequestParam("idProff") MultipartFile idProof,
			@RequestParam("domicile") MultipartFile domicile,
			@RequestParam("screenShotPath") String  screenShotPath) throws Exception {
		KendraKyc user = new KendraKyc();
		UserResponse response= new UserResponse();
		user.setName(name);
		user.setAddressLine1(addressLine1);
		user.setAddressLine2(addressLine2);
		user.setAdharNumber(adharNumber);
		user.setBloodgroup(bloodgroup);
		user.setDistrict(district);
		user.setDob(dob);
		user.setEmail(email);
		user.setFatherName(fatherName);
		user.setMaritalStatus(maritalStatus);
		user.setMedicalHistory(medicalHistory);
		user.setGender(gender);
		user.setMobile(mobile);
		user.setMotherName(motherName);
		user.setKendrarea(kendrarea);
		user.setOwnerAddress(ownerAddress);
		user.setPincode(pincode);
		user.setSpouseName(spouseName);
		user.setState(state);
		user.setQualification(qualification);
		user.setPancardNumber(pancardNumber);
		user.setTransactionNumber(transactionNumber);
		user.setBlock(block);
		user.setVillage(village);
		user.setScreenShotPath(null);

		kendraKycService.saveUser(user, adharCard, panCard, domicile, lpc, idProof);
		
		response.setStatus(true);
		response.setObject(user);
		response.setMessage("Kyc Detatil Created successFully ");
		System.out.println(response);
		return response;
	}

	@CrossOrigin()
	@PostMapping("/getuser/{email}")
	public KendraKyc getUserByEmail(@PathVariable String email){
		KendraKyc kycuser=kendraKycService.getUserByEmail(email);
		femail=kycuser.getEmail();
		fstate=kycuser.getState();
		dist=kycuser.getDistrict();
		fvillage=kycuser.getVillage();
		return kycuser;
	}
	
	
	
//	@CrossOrigin()
//	@GetMapping("/{id}")
//	public ResponseEntity<KendraKyc> getUserFileById(@PathVariable Long id) {
//		Optional<KendraKyc> userFileOptional = userService.getFileByUserId(id);
//		if (userFileOptional.isPresent()) {
//			return ResponseEntity.ok(userFileOptional.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	// to update the kendrakyc user
//
//	@PutMapping("/update/{id}")
//	public KendraKyc updateUser(@PathVariable("id") Long id, @RequestParam("name") String name,
//			@RequestParam("dob") String dob, @RequestParam("transactionNumber") String transactionNumber,
//			@RequestParam("village") String village, @RequestParam("block") String block,
//			@RequestParam("adharNumber") String adharNumber, @RequestParam("pancardNumber") String pancardNumber,
//			@RequestParam("mobile") String mobile, @RequestParam("email") String email,
//			@RequestParam("gender") String gender, @RequestParam("fatherName") String fatherName,
//			@RequestParam("motherName") String motherName, @RequestParam("maritalStatus") String maritalStatus,
//			@RequestParam("spouseName") String spouseName, @RequestParam("ownerAddress") String ownerAddress,
//			@RequestParam("addressLine1") String addressLine1, @RequestParam("addressLine2") String addressLine2,
//			@RequestParam("kendrarea") String kendrarea, @RequestParam("state") String state,
//			@RequestParam("district") String district, @RequestParam("pincode") String pincode,
//			@RequestParam("bloodgroup") String bloodgroup, @RequestParam("medicalHistory") String medicalHistory,
//			@RequestParam("qualification") String qualification, @RequestParam("adharCard") MultipartFile adharCard,
//			@RequestParam("panCard") MultipartFile panCard, @RequestParam("lpc") MultipartFile lpc,
//			@RequestParam("idProof") MultipartFile idProof, @RequestParam("domicile") MultipartFile domicile,
//			@RequestParam("screenShotPath") MultipartFile screenShotPath) throws Exception {
//		KendraKyc user = userService.getUserById(id);
//		if (user == null) {
//			throw new Exception("User not found");
//		}
//		
//		user.setName(name);
//		user.setAddressLine1(addressLine1);
//		user.setAddressLine2(addressLine2);
//		user.setAdharNumber(adharNumber);
//		user.setBloodgroup(bloodgroup);
//		user.setDistrict(district);
//		user.setDob(dob);
//		user.setEmail(email);
//		user.setFatherName(fatherName);
//		user.setMaritalStatus(maritalStatus);
//		user.setMedicalHistory(medicalHistory);
//		user.setGender(gender);
//		user.setMobile(mobile);
//		user.setMotherName(motherName);
//		user.setKendrarea(kendrarea);
//		user.setOwnerAddress(ownerAddress);
//		user.setPincode(pincode);
//		user.setSpouseName(spouseName);
//		user.setState(state);
//		user.setQualification(qualification);
//		user.setPancardNumber(pancardNumber);
//		user.setTransactionNumber(transactionNumber);
//		user.setBlock(block);
//		user.setVillage(village);
//
//		return userService.saveUserWthScreenshot(user, adharCard, panCard, domicile, lpc, idProof, screenShotPath);
//	}
	
	
	@CrossOrigin()
	@PutMapping("/update/{id}")
	public UserResponse updateKendraKyc(@PathVariable Long id,
			@RequestParam("transactionNumber") String transactionNumber,
			@RequestParam("screenShotPath") MultipartFile screenShotPath) throws IOException {
		
		
		UserResponse response = new UserResponse();
		
		KendraKyc user =kendraKycService.getUserById(id);
		if(user != null) {
			user.setTransactionNumber(transactionNumber);
			if(screenShotPath != null && !screenShotPath.isEmpty()) {
				String screePath=saveFile(screenShotPath);
				user.setScreenShotPath(screePath);
			}
			kendraKycService.updateUser(user);
			response.setStatus(true);
			response.setObject(user);
			response.setMessage("Your ScreenShot And Transaction Number Updated Successfully");
		}else {
			response.setStatus(true);
			response.setObject(null);
			response.setMessage("Your ScreenShot And Transaction Number Not Updated Successfully");
		}
		return response;
		
	}
	
	private String saveFile(MultipartFile file) throws IOException {
		KendraUser fik = us.getUserByEmail(femail);
		System.out.println(fik);
		File directory = new File(
				storageDirectory + "//" + fstate + "//" + dist + "//"+fvillage+"//" + fik.getUsername() + "_" + fik.getEmail()+"//Trx");

		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("Directory created successfully!");
			} else {
				System.out.println("Failed to create directory!");
			}
		} else {
			System.out.println("Directory already exists.");
		}

		String fileName = file.getOriginalFilename();
		String filePath = directory + "//" + fileName;
		File dest = new File(filePath);
		file.transferTo(dest);
		return filePath;
	}
}
