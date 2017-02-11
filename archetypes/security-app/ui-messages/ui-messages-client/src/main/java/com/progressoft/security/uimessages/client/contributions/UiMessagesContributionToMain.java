package com.progressoft.security.uimessages.client.contributions;

import com.progressoft.security.uimessages.client.requests.ApplyUiMessagesContributionsClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Contribute
public class UiMessagesContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new ApplyUiMessagesContributionsClientRequest(extensionPoint.context()).send();
    }
}
