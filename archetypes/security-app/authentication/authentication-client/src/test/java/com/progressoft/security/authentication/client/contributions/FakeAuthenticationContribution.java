package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@Contribute
public class FakeAuthenticationContribution implements Contribution<AuthenticationExtensionPoint> {

    public static final String LOGIN="LOGIN";

    protected AuthenticationContext context;
    public FakeAuthenticationProvider provider;

    private static CoreLogger LOGGER= CoreLoggerFactory.getLogger(FakeAuthenticationContribution.class);

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
