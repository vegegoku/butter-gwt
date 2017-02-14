package com.progressoft.security.app.layout.client.contributions;

import com.progressoft.security.app.layout.client.requests.ObtainMainContextClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Contribute
public class AppLayoutToMainContribution implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new ObtainMainContextClientRequest(extensionPoint.context()).send();
    }
}
