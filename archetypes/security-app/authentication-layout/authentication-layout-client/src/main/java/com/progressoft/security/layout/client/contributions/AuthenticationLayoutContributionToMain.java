package com.progressoft.security.layout.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import com.progressoft.security.layout.client.requests.AuthenticationLayoutClientRequest;

@Contribute
public class AuthenticationLayoutContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new AuthenticationLayoutClientRequest(extensionPoint).send();
    }
}
