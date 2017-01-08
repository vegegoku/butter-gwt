package com.progressoft.security.authentication.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Contribute
public class AuthenticationContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {

    }
}
