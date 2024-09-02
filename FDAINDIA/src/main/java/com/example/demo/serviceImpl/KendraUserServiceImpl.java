package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserKendraDataDto;
import com.example.demo.entity.KendraUser;
import com.example.demo.repository.KendraUserRepository;
import com.example.demo.response.UserResponse;
import com.example.demo.service.KendraUserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.transaction.Transactional;

@Service
public class KendraUserServiceImpl implements KendraUserService {
	

	@Autowired
	private KendraUserRepository fdaIndiaKendraRepository;

	@Override
	public KendraUser searchByEmail(String email) {
		return fdaIndiaKendraRepository.findByEmail(email);
	}

	@Override
	public UserResponse registerUser(KendraUser user) {

		UserResponse response = new UserResponse();
		try {
			if (fdaIndiaKendraRepository.findByUsername(user.getUsername()) != null) {
				response.setStatus(false);
				response.setMessage("Username already exists");
				response.setObject(null);
				return response;
			}
			if (fdaIndiaKendraRepository.findByEmail(user.getEmail()) != null) {
				response.setStatus(false);
				response.setMessage("Email already exists");
				response.setObject(null);
				return response;
			}

			System.out.println(user);
			user.setToken(generateUniqueToken());
			fdaIndiaKendraRepository.save(user);
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

	@Override
	public UserResponse loginUser(String username, String password) {
		UserResponse response = new UserResponse();
		KendraUser userNameAndEmail = fdaIndiaKendraRepository.findByUserNameOrEmailAndPassword(username, username,
				password);
		try {
			if (userNameAndEmail != null) {
				userNameAndEmail.setPassword(null);
				response.setObject(userNameAndEmail);
				response.setStatus(true);
				response.setMessage("User Login Successfull");
			} else {
				response.setMessage("User Login Unsuccessful please Provide the proper username Or email");
				response.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	public UserKendraDataDto searchByEmailWithoutPass(String email) {
		return fdaIndiaKendraRepository.findByEmailWithoutPassword(email);
	}

	@Transactional
	@Override
	  public void updateFDAIndiaKendraByEmail(String email, KendraUser updatedKendra) {
        KendraUser existingKendra = fdaIndiaKendraRepository.findByEmail(email);
        if (existingKendra != null) {
            if (updatedKendra.getName() != null) {
                existingKendra.setName(updatedKendra.getName());
            }
            if (updatedKendra.getUsername() != null) {
                existingKendra.setUsername(updatedKendra.getUsername());
            }
            if (updatedKendra.getMobile() != null) {
                existingKendra.setMobile(updatedKendra.getMobile());
            }
            if (updatedKendra.getState() != null) {
                existingKendra.setState(updatedKendra.getState());
            }
            if (updatedKendra.getPassword() != null) {
                existingKendra.setPassword(updatedKendra.getPassword());
            }
            if (updatedKendra.getToken() != null) {
                existingKendra.setToken(updatedKendra.getToken());
            }
            if (updatedKendra.getKycStatus() != null) {
                existingKendra.setKycStatus(updatedKendra.getKycStatus());
            }
            fdaIndiaKendraRepository.save(existingKendra);
        }
    }
	
	   public Page<KendraUser> getUsers(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return fdaIndiaKendraRepository.findAll(pageable);
	    }

	@Override
	public KendraUser getUser(Long id) {
		return fdaIndiaKendraRepository.findByUserId(id) ;
	}
	
	public void generateQRCodes() throws WriterException, IOException {
        List<KendraUser> users = fdaIndiaKendraRepository.findAll();
        String qrCodeDir = "C:\\Users\\hp\\Videos\\QRCode";

        // Ensure the directory exists
        File directory = new File(qrCodeDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (KendraUser user : users) {
            String qrCodeContent = "User ID: " + user.getUserId() + "\nName: " + user.getName() + 
                                   "\nEmail: " + user.getEmail() + "\nUsername: " + user.getUsername()+
                                   "\ntotalAmount: "+user.getTotal_amount()+"\nkycStatus: "+user.getKycStatus()+
                                   "\nphoneNumber: "+user.getMobile()+"\nstate: "+user.getState()+
                                   "\ntoken :"+user.getToken();
            generateQRCodeImage(qrCodeContent, 350, 350, qrCodeDir + "\\" + user.getUsername() + ".png");
        }
    }

    private void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
