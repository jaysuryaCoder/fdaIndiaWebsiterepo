package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.KendraKyc;

public interface KendraKycService {
	public KendraKyc saveUser(KendraKyc user, MultipartFile adharCard, MultipartFile panCard, MultipartFile domicile,
			MultipartFile lpc, MultipartFile idProff) throws IOException;
	
	public String saveFile(MultipartFile file) throws IOException ;
	
	public Optional<KendraKyc> getFileByUserId(Long id) ;
	public KendraKyc getUserById(Long id);
	
	public KendraKyc getUserByEmail(String email);
	
	public KendraKyc saveUserWthScreenshot(KendraKyc user, MultipartFile adharCard, MultipartFile panCard,
			MultipartFile domicile, MultipartFile lpc, MultipartFile idProff, MultipartFile screenShotPath)
			throws IOException;
	
	public KendraKyc updateUser(KendraKyc user);
	
	public KendraKyc findByEmail(String email);

}
