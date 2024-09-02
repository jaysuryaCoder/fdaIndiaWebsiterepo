package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.UserDataDTO;
import com.example.demo.dto.UserKendraDataDto;
import com.example.demo.entity.FDAIndiaCard;
import com.example.demo.entity.KendraUser;
import com.example.demo.repository.CardRepsository;
import com.example.demo.response.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.KendraUserService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class FdaUserController {

	@Autowired
	private UserService userServices;
	
	@Autowired
	private CardRepsository userRepo;
	
	@Autowired
	private KendraUserService fdaIndiaKendraService;
	
	int count=1;
	@CrossOrigin()
	@GetMapping("/test")
	public String home() {
		System.out.println("Registration Hit: "+ count++);
		return "";
	}
	
	@CrossOrigin()
	@PostMapping("/registerforcard")
	public UserResponse registerForCard(@RequestBody FDAIndiaCard user) {
		return userServices.registerUser(user);
	}
	
	@CrossOrigin()
	@PostMapping("/registerforkendra")
	public UserResponse registerForKendra(@RequestBody KendraUser user) {
		return fdaIndiaKendraService.registerUser(user);
	}
	
	@CrossOrigin()
	@PostMapping("/loginforcard")
	public UserResponse loginForCard(@RequestBody UserRequest request) {
		System.out.println(request);
		return userServices.loginUser(request.getUsername(),request.getPassword());
	}
	
	@CrossOrigin()
	@PostMapping("/loginforkendra")
	public UserResponse loginForKendra(@RequestBody UserRequest request) {
		System.out.println(request);
		return fdaIndiaKendraService.loginUser(request.getUsername(),request.getPassword());
	}
	
	@CrossOrigin()
	@PostMapping("/hrmslogin")
	public String  hrmsUser(@RequestBody UserRequest request) {
		System.out.println(request.getUsername() +" " +request.getPassword());
		if(request.getUsername().equals("ADMIN") && request.getPassword().equals("Fdaindia@2024")) {
			return "success";
		}
		return "Failed";
	}
	
	@CrossOrigin()
	@PostMapping("/allKendraUsers")
	public List<UserDataDTO> getAllUser(){
		return userServices.getAllUser();
	}
	
	@CrossOrigin()
	@PutMapping("/update/{id}")
	public UserResponse updateEmployee(@PathVariable Long id,@RequestBody FDAIndiaCard fdaIndiaUser) {
		return userServices.updateUser(id, fdaIndiaUser);
	}
	
	@CrossOrigin()
	@PostMapping("/token/{token}")
	public FDAIndiaCard getUserByToken(@PathVariable Long token) {
		return userServices.getUserByToken(token);
	}


	@CrossOrigin()
	@PostMapping("/byEmailCard/{email}")
	public FDAIndiaCard findByEmailId(@PathVariable String email) {
		return userRepo.findByEmail(email);
	}
		
	@CrossOrigin()
	@PostMapping("/byEmailKendra/{email}")
	public UserKendraDataDto getByEmailWithoutPass(@PathVariable String email) {
		return fdaIndiaKendraService.searchByEmailWithoutPass(email);
	}
}