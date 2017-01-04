package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeAuthenticationCompletedContribution implements Contribution<AuthenticationCompletedExtensionPoint> {

    private AuthenticationCompletedContext context;

    @Override
    public void contribute(AuthenticationCompletedExtensionPoint extensionPoint) {
        context = extensionPoint.context();
    }

    public AuthenticationCompletedContext getContext() {
        return context;
    }
}
