package com.progressoft.security.model.otp;


import com.progressoft.notification.configuration.SmtpConfiguration;
import com.progressoft.notification.smtp.EmailMessage;

import java.util.Collections;

public class SimpleOtpHolder implements OtpHolder {
    private final String code;
    private final int timeout;
    private final long generationTime;
    private final SmtpConfiguration smtpConfiguration;

    public SimpleOtpHolder(String code, int timeout, SmtpConfiguration smtpConfiguration) {
        this.code = code;
        this.timeout = timeout;
        this.smtpConfiguration = smtpConfiguration;
        this.generationTime = getCurrentTimeMillis();
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public boolean verify(String code) {
        if (!this.code.equals(code))
            return false;
        if (getCurrentSeconds() > secondsSinceGenerationPlusTimeOut())
            return false;
        return true;
    }

    @Override
    public void sendEmail(String email) {
        EmailMessage message = new EmailMessage("Verification code", code);
        message.send(smtpConfiguration.systemEmail(), Collections.singletonList(email));
    }

    private long secondsSinceGenerationPlusTimeOut() {
        return (generationTime + (timeout * 1000)) / 1000;
    }

    private long getCurrentSeconds() {
        return getCurrentTimeMillis() / 1000;
    }
}