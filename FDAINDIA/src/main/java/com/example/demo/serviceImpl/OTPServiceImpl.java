package com.example.demo.serviceImpl;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.demo.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService{
	
	private Map<String, Integer> otpStorage = new ConcurrentHashMap<>();
	private Map<String, Long> otpStorage1 = new ConcurrentHashMap<>();

    public int generateOTP(String email) {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        otpStorage.put(email, otp);
        return otp;
    }

    public boolean verifyOTP(String email, int otp) {
        if (otpStorage.containsKey(email) && otpStorage.get(email) == otp) {
            otpStorage.remove(email);
            return true;
        }
        return false;
    }
    
    public Long generateOTPLong(String email) {
        long otp = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
        otpStorage1.put(email, otp);
        return otp;
    }
}
