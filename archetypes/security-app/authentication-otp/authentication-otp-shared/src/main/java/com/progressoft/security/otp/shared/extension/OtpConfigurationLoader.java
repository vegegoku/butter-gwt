package com.progressoft.security.otp.shared.extension;

@FunctionalInterface
public interface OtpConfigurationLoader {
    OtpConfiguration load();
}
