package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.KendraUser;
import com.example.demo.service.KendraUserService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/kendra")
public class KendraUserController {
	
	@Autowired
	private KendraUserService service;
	
	   @PutMapping("/update/{email}")
	    public ResponseEntity<String> updateFDAIndiaKendraByEmail(@PathVariable("email") String email, @RequestBody KendraUser kendra) {
	        service.updateFDAIndiaKendraByEmail(email, kendra);
	        return ResponseEntity.ok("User updated successfully");
	    }
	   
	   	@CrossOrigin()
	    @GetMapping("/users")
	    public Page<KendraUser> getUsers(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "20") int size) {
	        return service.getUsers(page, size);
	    }
	   	
	   	
	   	@CrossOrigin
	   	@GetMapping("/users/{id}")
	   	public KendraUser getUSer(@PathVariable Long  id) {
	   		return service.getUser(id);
	   	}
	   	
	    @GetMapping("/generate-qrcodes")
	    public String generateQRCodes() {
	        try {
	        	service.generateQRCodes();
	            return "QR Codes generated successfully.";
	        } catch (WriterException | IOException e) {
	            e.printStackTrace();
	            return "Error occurred while generating QR codes.";
	        }
	    }
}