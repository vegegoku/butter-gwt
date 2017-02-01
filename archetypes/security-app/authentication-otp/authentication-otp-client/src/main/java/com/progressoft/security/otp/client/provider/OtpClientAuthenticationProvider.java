package com.progressoft.security.otp.client.provider;

import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import com.progressoft.security.otp.client.requests.BeginOtpAuthenticationClientRequest;

public class OtpClientAuthenticationProvider implements ClientAuthenticationProvider{

    public static final String OTP ="OTP";

    @Override
    public void begin() {
        new BeginOtpAuthenticationClientRequest().send();
    }
}
