package com.progressoft.security.otp.client.contributions;

import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import com.progressoft.security.otp.client.requests.ObtainAuthenticationLayoutClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class OtpContributionToAuthenticationLayout implements Contribution<AuthenticationLayoutExtensionPoint> {

    @Override
    public void contribute(AuthenticationLayoutExtensionPoint extensionPoint) {
        new ObtainAuthenticationLayoutClientRequest(extensionPoint.context()).send();
    }
}
