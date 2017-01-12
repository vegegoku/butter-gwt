package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;

public class SecondFakeAuthenticationContribution extends FakeAuthenticationContribution{

    public static final String SECOND_CHAIN="SECOND_CHAIN";

    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {
        context=extensionPoint.context();
        provider=new FakeAuthenticationProvider(context);
        context.registerProvider(SECOND_CHAIN, provider);
    }
}
