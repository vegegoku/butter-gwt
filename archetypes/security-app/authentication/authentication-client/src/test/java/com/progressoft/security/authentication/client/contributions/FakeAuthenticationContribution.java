package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeAuthenticationContribution implements Contribution<AuthenticationExtensionPoint> {

    private AuthenticationContext context;

    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {
        context = extensionPoint.context();
    }

    public AuthenticationContext getContext() {
        return context;
    }
}
