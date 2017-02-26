package com.progressoft.security.userinfo.client.contributions;

import com.google.gwt.user.client.Window;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.userinfo.client.requests.ObtainAppLayoutContextClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class UserInfoToAppLayoutContribution implements Contribution<AppLayoutExtensionPoint> {
    @Override
    public void contribute(AppLayoutExtensionPoint extensionPoint) {
        new ObtainAppLayoutContextClientRequest(extensionPoint.context()).send();
    }
}
