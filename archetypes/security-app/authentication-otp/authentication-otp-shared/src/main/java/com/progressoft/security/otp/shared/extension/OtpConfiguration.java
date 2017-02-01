package com.progressoft.security.otp.shared.extension;

public interface OtpConfiguration {
    int timeout();

    int digitsCount();

    class InvalidTimeoutProvidedException extends RuntimeException{}

    class InvalidDigitsCountProvidedException extends RuntimeException{}
}
