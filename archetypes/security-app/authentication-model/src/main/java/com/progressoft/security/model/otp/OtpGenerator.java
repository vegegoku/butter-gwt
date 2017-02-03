package com.progressoft.security.model.otp;

@FunctionalInterface
public interface OtpGenerator {
    OtpHolder generate();
}
