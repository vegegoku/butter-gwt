package com.progressoft.security.otp.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class DefaultVerifyOtpRequest extends ServerRequest {
    private String otpCode;

    public DefaultVerifyOtpRequest() {
        // default constructor for GWT serialization
    }

    public DefaultVerifyOtpRequest(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}

