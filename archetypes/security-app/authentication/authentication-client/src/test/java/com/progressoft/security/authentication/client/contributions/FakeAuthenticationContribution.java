package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeAuthenticationContribution implements Contribution<AuthenticationExtensionPoint> {

    public static final String LOGIN="LOGIN";

    private AuthenticationContext context;
    private FakeAuthenticationProvider provider;

    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {
        context=extensionPoint.context();
        provider=new FakeAuthenticationProvider(context);
        context.registerProvider(LOGIN, provider);
    }

    public AuthenticationContext getContext() {
        return context;
    }

    public FakeAuthenticationProvider getProvider() {
        return provider;
    }
}
