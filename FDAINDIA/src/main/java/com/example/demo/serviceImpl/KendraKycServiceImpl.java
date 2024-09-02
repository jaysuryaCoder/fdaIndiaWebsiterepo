package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.KendraUser;
import com.example.demo.entity.KendraKyc;
import com.example.demo.repository.KendraKycRepository;
import com.example.demo.service.KendraKycService;
import com.example.demo.service.UserService;

@Service
public class KendraKycServiceImpl implements KendraKycService {
	
	@Autowired
	private KendraKycRepository userRepository;
	@Autowired
	private UserService us;

	private static String email;
	private static String fstate;
	private static String dist;
	private static String village;

	private final String storageDirectory = "C:\\Fda\\KYCData\\KendraKycDocument\\";

	public KendraKyc saveUser(KendraKyc user, MultipartFile adharCard, MultipartFile panCard, MultipartFile domicile,
			MultipartFile lpc, MultipartFile idProff) throws IOException {
		System.out.println("From KYC Table: " + user.getEmail());
		email = user.getEmail();
		fstate = user.getState();
		dist = user.getDistrict();
		village=user.getVillage();
		if (adharCard != null && !adharCard.isEmpty()) {
			String adharCardPath = saveFile(adharCard);
			user.setAdharCardPath(adharCardPath);
		}

		if (panCard != null && !panCard.isEmpty()) {
			String panCardPath = saveFile(panCard);
			user.setPanCardPath(panCardPath);
		}

		if (domicile != null && !domicile.isEmpty()) {
			String domicilePath = saveFile(domicile);
			user.setDomicilePath(domicilePath);
		}

		if (lpc != null && !lpc.isEmpty()) {
			String lpcPath = saveFile(lpc);
			user.setLpcPath(lpcPath);
		}

		if (idProff != null && !idProff.isEmpty()) {
			String idProofPath = saveFile(idProff);
			user.setIdProofPath(idProofPath);
		}

		return userRepository.save(user);
	}

	public String saveFile(MultipartFile file) throws IOException {
	 KendraUser	fiu = us.getUserByEmail(email);
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

	public Optional<KendraKyc> getFileByUserId(Long id) {
		return userRepository.getUserFileByUserId(id);
	}

	public KendraKyc getUserById(Long id) {
		return userRepository.findByUserId(id);
	}
	
	public KendraKyc getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	public KendraKyc saveUserWthScreenshot(KendraKyc user, MultipartFile adharCard, MultipartFile panCard,
			MultipartFile domicile, MultipartFile lpc, MultipartFile idProff, MultipartFile screenShotPath)
			throws IOException {
		System.out.println("From KYC Table: " + user.getEmail());
		email = user.getEmail();
		fstate = user.getState();
		dist = user.getDistrict();
		if (adharCard != null && !adharCard.isEmpty()) {
			String adharCardPath = saveFile(adharCard);
			user.setAdharCardPath(adharCardPath);

		}


		if (panCard != null && !panCard.isEmpty()) {
			String panCardPath = saveFile(panCard);
			user.setPanCardPath(panCardPath);
		}

		if (domicile != null && !domicile.isEmpty()) {
			String domicilePath = saveFile(domicile);
			user.setDomicilePath(domicilePath);
		}

		if (lpc != null && !lpc.isEmpty()) {
			String lpcPath = saveFile(lpc);
			user.setLpcPath(lpcPath);
		}

		if (idProff != null && !idProff.isEmpty()) {
			String idProofPath = saveFile(idProff);
			user.setIdProofPath(idProofPath);
		}

		if (screenShotPath != null && !screenShotPath.isEmpty()) {
			String idProofPath = saveFile(screenShotPath);
			user.setIdProofPath(idProofPath);
		}

		return userRepository.save(user);
	}

	public KendraKyc updateUser(KendraKyc user) {
		return userRepository.save(user);
	}
	
	public KendraKyc findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
