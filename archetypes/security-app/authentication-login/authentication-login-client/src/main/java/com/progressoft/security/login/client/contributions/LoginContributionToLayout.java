package com.progressoft.security.login.client.contributions;

import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import com.progressoft.security.login.client.requests.ObtainLayoutClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class LoginContributionToLayout implements Contribution<AuthenticationLayoutExtensionPoint>{
    @Override
    public void contribute(AuthenticationLayoutExtensionPoint extensionPoint) {
        new ObtainLayoutClientRequest(extensionPoint.context()).send();
    }
}
