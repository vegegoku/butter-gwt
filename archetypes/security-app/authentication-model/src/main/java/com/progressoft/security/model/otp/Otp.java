package com.progressoft.security.model.otp;

import com.progressoft.notification.configuration.SmtpConfigurationContext;

import static java.util.Objects.*;

public class Otp implements OtpGenerator{

    private static final OtpHolderFactory defaultHolderFactory=
            SimpleOtpHolder::new;
    public static final int TRUNCATION_OFFSET = 16;
    public static final boolean ADD_CHECKSUM = false;
    private final int digitsCount;
    private final String secret;
    private final int timeout;

    public Otp(String secret, int digitsCount, int timeout) {
        validateSecret(secret);
        validateDigitsCount(digitsCount);
        validateTimeOut(timeout);

        this.secret=secret;
        this.digitsCount =digitsCount;
        this.timeout=timeout;
    }

    private void validateTimeOut(int timeout) {
        if(timeout<=0)
            throw new InvalidTimeoutProvidedException();
    }

    private void validateDigitsCount(int digitsCount) {
        if(digitsCount<4)
            throw new DigitsCountLessAllowedMinimumException("4");
    }

    private void validateSecret(String secret) {
        if(isNull(secret) || secret.trim().isEmpty())
            throw new InvalidSecretProvidedException();
    }

    @Override
    public OtpHolder generate(){
        return generate(defaultHolderFactory);
    }

    private long movingFactor() {
        return System.currentTimeMillis();
    }

    public OtpHolder generate(OtpHolderFactory otpHolderFactory){
        return otpHolderFactory.make(generatedCode(), timeout, SmtpConfigurationContext.configure());
    }

    private String generatedCode() {
        return HOTPAlgorithm.generateOTP(secret.getBytes(), movingFactor(), digitsCount, ADD_CHECKSUM, TRUNCATION_OFFSET);
    }

    public class InvalidSecretProvidedException extends RuntimeException{}

    public class DigitsCountLessAllowedMinimumException extends RuntimeException{
        public DigitsCountLessAllowedMinimumException(String message) {
            super(message);
        }
    }

    public class InvalidTimeoutProvidedException extends RuntimeException {}
}
