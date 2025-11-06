package com.maaz.Java_ToDoApp.service;

import com.maaz.Java_ToDoApp.dto.auth.SendOtpResponse;
import com.maaz.Java_ToDoApp.dto.auth.VerifyOtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, OtpData> otpStorage = new HashMap<>();

    // Inner class to store OTP and expiry time
    private static class OtpData {
        String otp;
        LocalDateTime expiryTime;

        OtpData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    public SendOtpResponse sendOtp(String email) {
        // normalize email
        email = email.toLowerCase();

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5); // 5 min validity

        otpStorage.put(email, new OtpData(otp, expiry));

        // Send OTP via email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\n\nThis code will expire in 5 minutes.");
        mailSender.send(message);

        System.out.println("✅ Stored OTP for: " + email + " = " + otp);
        System.out.println("All stored keys: " + otpStorage.keySet());

        return new SendOtpResponse(true, "OTP sent successfully! It will expire in 5 minutes.");
    }

    public Boolean validateOtp(VerifyOtpRequest request) {

        System.out.println(otpStorage.get(request.email().toLowerCase()));
        OtpData data = otpStorage.get(request.email().toLowerCase());

        if (data == null) return  false;
        if (LocalDateTime.now().isAfter(data.expiryTime)) {
            otpStorage.remove(request.email());
            return false; // expired
        }

        return data.otp.equals(String.valueOf(request.otp()));
//        return isValid ? new VerifyOtpResponse(true,"Otp verified"):new VerifyOtpResponse(false,"Otp not verified");
    }

}
