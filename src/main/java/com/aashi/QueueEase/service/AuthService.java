package com.aashi.QueueEase.service;
import org.springframework.stereotype.Service;

import com.aashi.QueueEase.repository.UserRepository;
import com.aashi.QueueEase.repository.OtpVerificationRepository;
import com.aashi.QueueEase.util.JwtUtil;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aashi.QueueEase.dto.AuthResponse;
import com.aashi.QueueEase.dto.LoginRequest;
import com.aashi.QueueEase.dto.OtpVerifyRequest;
import com.aashi.QueueEase.dto.RegisterRequest;


import com.aashi.QueueEase.entity.User;
import com.aashi.QueueEase.entity.OtpVerification;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
 private OtpVerificationRepository otpverifyrepo;
 @Autowired
 private PasswordEncoder passwordEncoder;
 @Autowired
private EmailService emailservice;
@Autowired
private JwtUtil jwtutil;

public String register(RegisterRequest request)
{
    if(userRepository.existsByEmail(request.getEmail()))
    {
        throw new RuntimeException("user already register with this email");
    }
    User user=new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
    user.setRole(User.Role.CUSTOMER);
    user.setAuthProvider(User.AuthProvider.LOCAL);
    user.setVerified(false);
    userRepository.save(user);
    String otp = String.valueOf(100000 + new Random().nextInt(900000));
    OtpVerification otpver=new OtpVerification();
    otpver.setEmail(request.getEmail());
    otpver.setOtpCode(otp);
    otpver.setExpiresAt(LocalDateTime.now().plusMinutes(5));
    otpver.setVerified(false);
    otpverifyrepo.save(otpver);
    emailservice.sendOtpEmail(request.getEmail(), otp);
    return "Registration successful. OTP sent to your email.";
}
public String verifyOtp(OtpVerifyRequest request)
{
OtpVerification otpRecord = otpverifyrepo.findTopByEmailOrderByCreatedAtDesc(request.getEmail())
       .orElseThrow(() -> new RuntimeException("No OTP found for this email"));
       if(LocalDateTime.now().isAfter(otpRecord.getExpiresAt()))
       {
        throw new RuntimeException("OTP expired");
       }
       if(!otpRecord.getOtpCode().equals(request.getOtp()))
       {
        throw new RuntimeException("Invalid OTP");
       }
       otpRecord.setVerified(true);
       otpverifyrepo.save(otpRecord);
       User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
       user.setVerified(true);
       userRepository.save(user);
       return "Email verified successfully. You can now log in.";
}
public AuthResponse login(LoginRequest request)
{
    User user = userRepository.findByEmail(request.getEmail())
       .orElseThrow(() -> new RuntimeException("Invalid email or password"));
       if(!user.isVerified())
       {
        throw new RuntimeException("Please verify your email before logging in");
       }
       if(!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
       {
        throw new RuntimeException("Invalid email or password");
       }
       String token = jwtutil.generateToken(user.getEmail(), user.getRole().name());
       return new AuthResponse(token, user.getEmail(), user.getRole().name(), "Login successful");
}
    
}
