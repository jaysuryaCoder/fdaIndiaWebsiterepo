package com.example.demo.service;

import com.example.demo.entity.Admin;

public interface AdminService {

	public Admin signUp(Admin admin);

	public Admin login(String usernameOrEmail,  String password);
	
	public String getEmailByUsername(String username);
}
