package com.progressoft.security.otp.client.presenters;

@FunctionalInterface
public interface VerifyHandler {
    void handle(String otpCode);
}
