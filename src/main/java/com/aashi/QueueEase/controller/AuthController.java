package com.aashi.QueueEase.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.aashi.QueueEase.dto.AuthResponse;
import com.aashi.QueueEase.dto.LoginRequest;
import com.aashi.QueueEase.dto.OtpVerifyRequest;
import com.aashi.QueueEase.dto.RegisterRequest;
import com.aashi.QueueEase.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    String result = authService.register(request);
    return ResponseEntity.ok(result);
}

@PostMapping("/verify-otp")
public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerifyRequest request) {
    String result = authService.verifyOtp(request);
    return ResponseEntity.ok(result);
}

@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    AuthResponse result = authService.login(request);
    return ResponseEntity.ok(result);
}
}
