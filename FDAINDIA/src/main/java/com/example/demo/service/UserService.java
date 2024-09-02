package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDataDTO;
import com.example.demo.entity.FDAIndiaCard;
import com.example.demo.entity.KendraUser;
import com.example.demo.response.UserResponse;

public interface UserService {
	public UserResponse registerUser(FDAIndiaCard user);
	public UserResponse loginUser(String username, String password);
	public List<UserDataDTO> getAllUser();
	public UserResponse updateUser(Long id, FDAIndiaCard fdaIndiaUser) ;
	public FDAIndiaCard getUserByToken(Long token);
	public KendraUser getUserByEmail(String email);
	public FDAIndiaCard findByEmail(String email);


}
