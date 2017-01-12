package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;

public class ThirdFakeAuthenticationContribution extends FakeAuthenticationContribution{

    public static final String THIRD_CHAIN="THIRD_CHAIN";

    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {
        context=extensionPoint.context();
        provider=new NamedFakeChainProvider(THIRD_CHAIN, context);
        context.registerProvider(THIRD_CHAIN, provider);
    }
}
