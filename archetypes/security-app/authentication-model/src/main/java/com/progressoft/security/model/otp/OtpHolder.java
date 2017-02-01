package com.progressoft.security.model.otp;

public interface OtpHolder {
    boolean verify(String code);
    void sendEmail(String email);
}
