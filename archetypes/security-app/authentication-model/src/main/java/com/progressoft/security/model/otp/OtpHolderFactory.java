package com.progressoft.security.model.otp;

import com.progressoft.notification.configuration.SmtpConfiguration;

@FunctionalInterface
public interface OtpHolderFactory {
    OtpHolder make(String code, int timeout, SmtpConfiguration configuration);
}
