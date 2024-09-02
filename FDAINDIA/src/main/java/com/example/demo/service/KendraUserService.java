package com.example.demo.service;



import java.io.IOException;

import org.springframework.data.domain.Page;

import com.example.demo.dto.UserKendraDataDto;
import com.example.demo.entity.KendraUser;
import com.example.demo.response.UserResponse;
import com.google.zxing.WriterException;

public interface KendraUserService {
	public KendraUser searchByEmail(String email);

	public UserResponse registerUser(KendraUser user);

	public UserResponse loginUser(String username, String password);

	public UserKendraDataDto searchByEmailWithoutPass(String email);

	public void updateFDAIndiaKendraByEmail(String email, KendraUser updatedKendra);

	public Page<KendraUser> getUsers(int page, int size);
	
	public KendraUser getUser(Long id);
	public void generateQRCodes() throws WriterException, IOException;

}
