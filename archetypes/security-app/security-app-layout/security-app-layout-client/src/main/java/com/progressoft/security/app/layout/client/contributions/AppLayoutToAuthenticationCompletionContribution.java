package com.progressoft.security.app.layout.client.contributions;

import com.progressoft.security.app.layout.client.requests.ObtainAuthenticationCompletedContextClientRequest;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class AppLayoutToAuthenticationCompletionContribution implements Contribution<AuthenticationCompletedExtensionPoint> {
    @Override
    public void contribute(AuthenticationCompletedExtensionPoint extensionPoint) {
        new ObtainAuthenticationCompletedContextClientRequest(extensionPoint.context()).send();
    }
}
