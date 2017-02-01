package com.progressoft.security.otp.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import com.progressoft.security.authentication.shared.extension.CompletedChainContext;
import com.progressoft.security.authentication.shared.extension.FailedChainContext;
import com.progressoft.security.otp.client.provider.OtpClientAuthenticationProvider;

public class FakeAuthenticationContext implements AuthenticationContext {

    public static final String PROVIDER_REGISTERED = "provider-registered";
    public static final String CHAIN_COMPLETED = "-chain-completed";
    public static final String CHAIN_FAILED = "-chain-failed";
    public OtpClientAuthenticationProvider provider;

    public StringBuilder calls=new StringBuilder("");

    @Override
    public void registerProvider(String name, ClientAuthenticationProvider authenticationProvider) {
        calls.append(PROVIDER_REGISTERED);
        provider=(OtpClientAuthenticationProvider)authenticationProvider;
    }

    @Override
    public void onChainCompleted(CompletedChainContext context) {
        calls.append(CHAIN_COMPLETED);
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        calls.append(CHAIN_FAILED);
    }
}