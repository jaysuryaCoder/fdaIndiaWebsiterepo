package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FdaEmployee;
import com.example.demo.repository.FdaEmployeeRepository;
import com.example.demo.service.FdaEmployeeService;

@Service
public class FdaEmployeeServiceImpl implements FdaEmployeeService {
	
	@Autowired
	private FdaEmployeeRepository fdaEmployeeRepository;
	private final String storageDirectory = "C:\\Users\\hp\\OneDrive\\Desktop\\jaysurya";

	public FdaEmployee createRmployee(FdaEmployee fdaEmployee, MultipartFile filePhoto) throws IOException {
		if (filePhoto != null && !filePhoto.isEmpty()) {
			String photo = saveFile(filePhoto);
			fdaEmployee.setPhotoPath(photo);
		}
		return fdaEmployeeRepository.save(fdaEmployee);
	}

	public String saveFile(MultipartFile file) throws IOException {
		File directory = new File(storageDirectory);

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
	
	public Optional<FdaEmployee> getEmployeeById(Long id){
		return fdaEmployeeRepository.findById(id);
	}
}
