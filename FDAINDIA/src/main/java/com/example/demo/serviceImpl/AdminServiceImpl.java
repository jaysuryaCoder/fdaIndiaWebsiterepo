package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;

	private final long admintoken = 123456789;

	@Override
	public Admin signUp(Admin admin) {
		Admin ad = new Admin();
		ad.setEmail(admin.getEmail());
		ad.setPassword(admin.getPassword());
		ad.setUsername(admin.getUsername());
		ad.setToken(admintoken);
		return repository.save(ad);
	}

	public Admin login(String usernameOrEmail, String password) {
		return repository.findByUsernameOrEmailAndPassword(usernameOrEmail, usernameOrEmail, password);
	}

	@Override
	public String getEmailByUsername(String username) {
		
		Admin admin=repository.findByUsername(username);
		if(admin!=null) {
			return admin.getEmail();
		}else {
			return "username doest exists";
		}
	}

}
