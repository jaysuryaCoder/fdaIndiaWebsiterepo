package com.example.demo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FdaEmployee;
import com.example.demo.service.FdaEmployeeService;
import com.example.demo.utils.EmployeeCodeGenerator;

@RestController
@RequestMapping("/emp")
public class FdaEmployeeController {

	@Autowired
	private FdaEmployeeService fdaEmployeeService;

	@CrossOrigin()
	@PostMapping("/createEmp")
	public FdaEmployee createEmployee(@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("alternatePhoneNumber") String alternatePhoneNumber, @RequestParam("email") String email,
			@RequestParam("refferedBy") String refferedBy, @RequestParam("bloodGroup") String bloodGroup,
			@RequestParam("fatherName") String fatherName, @RequestParam("motherName") String motherName,
			@RequestParam("relativeNumber") String relativeNumber,
			@RequestParam("permanentAddress") String permanentAddress, @RequestParam("adharCard") String adharCard,
			@RequestParam("temporaryAddress") String temporaryAddress, @RequestParam("panCard") String panCard,
			@RequestParam("password") String password, @RequestParam("location") String location,
			@RequestParam("designation") String designation, @RequestParam("photoPath") MultipartFile photoPath)
			throws IOException {

		FdaEmployee emp = new FdaEmployee();
		emp.setName(name);
		emp.setAdharCard(adharCard);
		emp.setAlternatePhoneNumber(alternatePhoneNumber);
		emp.setBloodGroup(bloodGroup);
		emp.setDesignation(designation);
		emp.setEmail(email);
		emp.setFatherName(fatherName);
		emp.setMotherName(motherName);
		emp.setLocation(location);
		emp.setRelativeNumber(relativeNumber);
		emp.setTemporaryAddress(temporaryAddress);
		emp.setRefferedBy(refferedBy);
		emp.setPhoneNumber(alternatePhoneNumber);
		emp.setPanCard(panCard);
		emp.setPassword(password);
		emp.setPermanentAddress(permanentAddress);
		emp.setEmpCode(EmployeeCodeGenerator.generateEmployeeCode());
		return fdaEmployeeService.createRmployee(emp, photoPath);
	}
	
	
	@CrossOrigin()
	@GetMapping("/{id}")
	public Optional<FdaEmployee> getEmployee(@PathVariable Long id) {
		return fdaEmployeeService.getEmployeeById(id);
	}

}
