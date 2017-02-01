package com.progressoft.security.model.otp;

@FunctionalInterface
public interface OtpGeneratorFactory {
    OtpGenerator make(String secret);
}
