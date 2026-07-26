package com.aashi.QueueEase.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public class OtpVerifyRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String otp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}