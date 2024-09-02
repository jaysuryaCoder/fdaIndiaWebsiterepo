package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FdaEmployee;

public interface FdaEmployeeService {
	
	public FdaEmployee createRmployee(FdaEmployee fdaEmployee, MultipartFile filePhoto) throws IOException;
	public String saveFile(MultipartFile file) throws IOException;
	public Optional<FdaEmployee> getEmployeeById(Long id);
}
