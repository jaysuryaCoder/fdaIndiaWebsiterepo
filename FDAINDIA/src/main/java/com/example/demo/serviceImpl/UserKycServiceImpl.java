package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.CardKycEntity;
import com.example.demo.entity.FDAIndiaCard;
import com.example.demo.repository.CardKycRepository;
import com.example.demo.service.UserKycService;
import com.example.demo.service.UserService;

@Service
public class UserKycServiceImpl implements UserKycService {

	@Autowired
	private CardKycRepository userRepository;
	
	@Autowired
	private UserService userService;
	

	private static String email;
	private static String fstate;
	private static String dist;
	private static String village;

	private final String storageDirectory = "C:\\Fda\\KYCData\\CardKycDocument";

	public CardKycEntity saveUser(CardKycEntity user, MultipartFile adharCard, MultipartFile panCard, MultipartFile domicile,
			 MultipartFile idProff) throws IOException {
		System.out.println("From KYC Table: " + user.getEmail());
		email = user.getEmail();
		fstate = user.getState();
		dist = user.getDistrict();
		village=user.getVillage();
		if (adharCard != null && !adharCard.isEmpty()) {
			String adharCardPath = saveFile(adharCard);
			user.setAdharCard(adharCardPath);
		}

		if (panCard != null && !panCard.isEmpty()) {
			String panCardPath = saveFile(panCard);
			user.setPanCard(panCardPath);
		}

		if (domicile != null && !domicile.isEmpty()) {
			String domicilePath = saveFile(domicile);
			user.setDomicile(domicilePath);
		}

		if (idProff != null && !idProff.isEmpty()) {
			String idProofPath = saveFile(idProff);
			user.setIdProff(idProofPath);
		}

		return userRepository.save(user);
	}

	public String saveFile(MultipartFile file) throws IOException {
	 FDAIndiaCard fiu= userService.findByEmail(email);
		System.out.println(fiu);
		File directory = new File(storageDirectory + "//" + fstate + "//" + dist + "//"+ village +"//"+ fiu.getUsername() + "_"
				+ fiu.getEmail());

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

	
	public CardKycEntity saveUserWthScreenshot(CardKycEntity user, MultipartFile adharCard, MultipartFile panCard,
			MultipartFile domicile, MultipartFile idProff, MultipartFile screenShotPath)
			throws IOException {
		System.out.println("From KYC Table: " + user.getEmail());
		email = user.getEmail();
		fstate = user.getState();
		dist = user.getDistrict();
		if (adharCard != null && !adharCard.isEmpty()) {
			String adharCardPath = saveFile(adharCard);
			user.setAdharCard(adharCardPath);

		}
		if (panCard != null && !panCard.isEmpty()) {
			String panCardPath = saveFile(panCard);
			user.setPanCard(panCardPath);
		}

		if (domicile != null && !domicile.isEmpty()) {
			String domicilePath = saveFile(domicile);
			user.setDomicile(domicilePath);
		}

		if (idProff != null && !idProff.isEmpty()) {
			String idProofPath = saveFile(idProff);
			user.setIdProff(idProofPath);
		}

		if (screenShotPath != null && !screenShotPath.isEmpty()) {
			String idProofPath = saveFile(screenShotPath);
			user.setIdProff(idProofPath);
		}

		return userRepository.save(user);
	}
}