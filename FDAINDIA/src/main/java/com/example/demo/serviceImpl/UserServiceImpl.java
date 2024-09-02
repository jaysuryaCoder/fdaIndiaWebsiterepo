package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDataDTO;
import com.example.demo.entity.FDAIndiaCard;
import com.example.demo.entity.KendraUser;
import com.example.demo.repository.KendraUserRepository;
import com.example.demo.repository.CardRepsository;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private CardRepsository repository;
	
	@Autowired
	private KendraUserRepository fdaIndiaKendraRepository;

	@Override
	public UserResponse registerUser(FDAIndiaCard user) {
		UserResponse response = new UserResponse();
		try {
			if (repository.findByUsername(user.getUsername()) != null) {
				response.setStatus(false);
				response.setMessage("Username already exists");
				response.setObject(null);
				return response;
			}
			if (repository.findByEmail(user.getEmail()) != null) {
				response.setStatus(false);
				response.setMessage("Email already exists");
				response.setObject(null);
				return response;
			}

			System.out.println(user);
			user.setToken(generateUniqueToken());
			repository.save(user);
			response.setObject(user);
			response.setStatus(true);
			response.setMessage("User Register suceesfull");
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("user not Registered Successfully");
			response.setObject(null);
			response.setStatus(false);
		}

		return response;

	}

	private long generateUniqueToken() {
		long token;
		Random random = new Random();
		token = 1000000000000000L + (long) (random.nextDouble() * 9000000000000000L);
		return token;
	}
	
	@Override
	public UserResponse loginUser(String username, String password) {
		UserResponse response = new UserResponse();
		FDAIndiaCard userNameAndEmail = repository.findByUserNameOrEmailAndPassword(username, username, password);
		try {
			if (userNameAndEmail != null) {
				userNameAndEmail.setPassword(null);
				response.setObject(userNameAndEmail);
				response.setStatus(true);
				response.setMessage("User Login Successfull");
			} else {
				response.setMessage("username or email not matched!!");
				response.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@Override
	public List<UserDataDTO> getAllUser() {
		return repository.findAllUsersWithoutPassword();
		}
	
	
	@Override
	public UserResponse updateUser(Long id, FDAIndiaCard fdaIndiaUser) {
		FDAIndiaCard user = repository.findByUserId(id);
		UserResponse response= new UserResponse();
		if (user != null) {
			user.setUsername(fdaIndiaUser.getUsername());
			user.setEmail(fdaIndiaUser.getEmail());
			user.setName(fdaIndiaUser.getName());
			user.setMobile(fdaIndiaUser.getMobile());
			user.setState(fdaIndiaUser.getState());
			user.setToken(fdaIndiaUser.getToken());
			user.setTotalAmount(fdaIndiaUser.getTotalAmount());
			user.setBorrowMoney(fdaIndiaUser.getBorrowMoney());
			user.setTransaction(fdaIndiaUser.getTransaction());
			user.setDeposit(fdaIndiaUser.getDeposit());
			user.setWithdrawalAmount(fdaIndiaUser.getWithdrawalAmount());
			user.setPassword(fdaIndiaUser.getPassword());
			repository.save(user);
			response.setStatus(true);
			response.setObject(user);
			response.setMessage("User records update successfully");
		}else {
			response.setStatus(false);
			response.setObject(null);
			response.setMessage("User records not updated Succesfully");
		}
		return response;
	}
	
	@Override
	public FDAIndiaCard getUserByToken(Long token) {
		return repository.findByToken(token);
	}

	@Override
	public KendraUser getUserByEmail(String email) {
		return fdaIndiaKendraRepository.findByEmail(email);
	}

	@Override
	public FDAIndiaCard findByEmail(String email) {
	 return repository.findByEmail(email);
	}

}
