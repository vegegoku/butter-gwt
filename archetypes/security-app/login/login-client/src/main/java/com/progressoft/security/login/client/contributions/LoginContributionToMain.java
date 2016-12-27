package com.progressoft.security.login.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import com.progressoft.security.login.client.requests.LoginSampleClientRequest;

@Contribute
public class LoginContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new LoginSampleClientRequest(extensionPoint).send();
    }
}
