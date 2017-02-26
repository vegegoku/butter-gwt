package com.progressoft.security.logout.client.contributions;

import com.google.gwt.user.client.Window;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.logout.client.requests.ObtainAppLayoutClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class LogoutToAppLayoutContribution implements Contribution<AppLayoutExtensionPoint> {
    @Override
    public void contribute(AppLayoutExtensionPoint extensionPoint) {
        new ObtainAppLayoutClientRequest(extensionPoint.context()).send();
    }
}
