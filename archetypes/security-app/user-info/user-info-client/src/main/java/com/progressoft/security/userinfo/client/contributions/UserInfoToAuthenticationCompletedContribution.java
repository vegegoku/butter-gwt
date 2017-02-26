package com.progressoft.security.userinfo.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.userinfo.client.requests.ObtainAuthenticationCompletedContextClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class UserInfoToAuthenticationCompletedContribution implements Contribution<AuthenticationCompletedExtensionPoint> {
    @Override
    public void contribute(AuthenticationCompletedExtensionPoint extensionPoint) {
        new ObtainAuthenticationCompletedContextClientRequest(extensionPoint.context()).send();
    }
}
