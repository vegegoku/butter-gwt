package com.progressoft.security.login.client.contributions;

import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import com.progressoft.security.login.client.requests.LoginSampleClientRequest;

@Contribute
public class LoginAuthenticationContribution implements Contribution<AuthenticationExtensionPoint> {
    @Override
    public void contribute(AuthenticationExtensionPoint extensionPoint) {

    }
}
