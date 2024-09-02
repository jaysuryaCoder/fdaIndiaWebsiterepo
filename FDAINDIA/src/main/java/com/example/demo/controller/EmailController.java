package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.KendraUser;
import com.example.demo.repository.KendraUserRepository;
import com.example.demo.response.EmailRequest;
import com.example.demo.response.OTPRequest;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.OTPService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;
    
    @Autowired
    private OTPService otpService;
    
    @Autowired
    private KendraUserRepository fdaIndiaKendraRepository; 
    
    @CrossOrigin()
    @PostMapping("/send")
    public String sendEmailOtp(@RequestBody EmailRequest request) {
    	System.out.println("Your Email : "+request.getEmail());
        int otp = otpService.generateOTP(request.getEmail());
        String subject = "Your OTP Code that is provided by FDAINDIA ";
        String body = "Your OTP code is: " + otp;
       
        emailSenderService.sendEmail(request.getEmail(), subject, body);
  
        return "Email sent successfully to " + request.getEmail();
    }
   
    @CrossOrigin()
    @PostMapping("/sendForKendraRegistration")
    public String sendEmail(@RequestBody EmailRequest request) {
        String subject = "Swabhimaan Kendra Registration";
        KendraUser kendra = fdaIndiaKendraRepository.findByEmail(request.getEmail());
        
        String body = generateEmailContent(kendra);

        emailSenderService.sendEmail(request.getEmail(), subject, body);

        return "Email sent successfully to " + request.getEmail();
    }
    
    @CrossOrigin()
    @PostMapping("/sendForKendraKyc")
    public String sendEmailForKyc(@RequestBody EmailRequest request) {
        String subject = "Complete Your application for Swabhimaan Kendra";
        KendraUser kendra = fdaIndiaKendraRepository.findByEmail(request.getEmail());
        
        String body = generateKycContent(kendra);

        emailSenderService.sendEmail(request.getEmail(), subject, body);

        return "Email sent successfully to " + request.getEmail();
    }
    
    @CrossOrigin()
    @PostMapping("/sendForTransaction")
    public String sendEmailForTransaction(@RequestBody EmailRequest request) {
        String subject = "Payment Confirmation for Swabhimaan Kendra Registration";
        KendraUser kendra = fdaIndiaKendraRepository.findByEmail(request.getEmail());
        
        String body = generateHtmlForTransaction(kendra);

        emailSenderService.sendEmail(request.getEmail(), subject, body);

        return "Email sent successfully to " + request.getEmail();
    }

    @CrossOrigin()
    @PostMapping("/verify")
    public String verifyEmail(@RequestBody OTPRequest otpRequest) {
        boolean isVerified = otpService.verifyOTP(otpRequest.getEmail(), otpRequest.getOtp());
        
        if (isVerified) {
            return "Email verified successfully";
        } else {
            return "Invalid OTP";
        }
    }
    
    private String generateEmailContent(KendraUser kendra) {
        String template = "<html><body>"
                        + "<p>Dear %s,</p>"
                        + "<p>Congratulations! You have successfully registered for Swabhimaan Kendra. To complete the process, please fill out the KYC form in the portal.</p>"
                        + "<p>If you have any doubt, please feel free to contact us.</p>"
                        + "<p>Best regards,<br>FDA India</p>"
                        + "<img src='cid:logoImage' alt='FDA India Kendra' style='width:25%%; height:auto;' / ><br>"
                        + "<b>Website: www.fdaindia.org</b><br>"
                        + "<b>Telephone:+91-8527407839</b>"
                        + "</body></html>";
        return String.format(template, kendra.getName());
    }
    
    private String generateKycContent(KendraUser kendra) {
        String template = "<html><body>"
                        + "<p>Dear %s,</p>"
                        + "<p>Thank you for completing the KYC form for Swabhimaan Kendra. To complete your application, please proceed with the registration payment.</p>"
                        + "<p>You can make the payment through portal. If you encounter any issues or need assistance, please don't hesitate to reach out to us.</p>"
                        + "<p>Best regards,<br>FDA India</p>"
                        + "<img src='cid:logoImage' alt='FDA India Kendra' style='width:25%%; height:auto;' / ><br>"
                        + "<b>Website: www.fdaindia.org</b><br>"
                        + "<b>Telephone:+91-8527407839</b>"
                        + "</body></html>";
        return String.format(template, kendra.getName());
    }
    
    private String generateHtmlForTransaction(KendraUser kendra) {
        String template = "<html><body>"
                        + "<p>Dear %s,</p>"
                        + "<p>I hope this email finds you well. </p>"
                        + "<p>We are pleased to inform you that we have successfully received your payment of Rs [amount] for the Swabhimaan Kendra registration. The payment has been processed, and your registration is now complete.</p>"
                        + "<p>If you have any questions or need further assistance, please feel free to contact us.</p>"
                        + "<p>Thank you for your support and participation.</p>"
                        + "<p>Best regards,<br>FDA India</p>"
                        + "<img src='cid:logoImage' alt='FDA India Kendra' style='width:25%%; height:auto;' / ><br>"
                        + "<b>Website: www.fdaindia.org</b><br>"
                        + "<b>Telephone:+91-8527407839</b>"
                        + "</body></html>";
        return String.format(template, kendra.getName());
    }
}