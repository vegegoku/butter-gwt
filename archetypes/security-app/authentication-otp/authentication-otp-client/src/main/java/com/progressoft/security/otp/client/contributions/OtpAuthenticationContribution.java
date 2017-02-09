package com.progressoft.security.otp.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.otp.client.requests.AuthenticationContextRecievedClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

import static java.util.Objects.isNull;

@Contribute
public class OtpAuthenticationContribution implements Contribution<AuthenticationExtensionPoint> {

    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {
        if(isNull(extensionPoint.context()))
            throw new InvalidAuthenticationContextRecieved();
        new AuthenticationContextRecievedClientRequest(extensionPoint.context()).send();
    }

    public class InvalidAuthenticationContextRecieved extends RuntimeException{
    }
}
