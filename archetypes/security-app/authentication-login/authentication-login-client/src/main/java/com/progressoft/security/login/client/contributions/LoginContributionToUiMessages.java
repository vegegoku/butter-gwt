package com.progressoft.security.login.client.contributions;

import com.progressoft.security.login.client.requests.ObtainUiMessagesContextClientRequest;
import com.progressoft.security.uimessages.shared.extension.UiMessagesExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class LoginContributionToUiMessages implements Contribution<UiMessagesExtensionPoint> {
    @Override
    public void contribute(UiMessagesExtensionPoint extensionPoint) {
        new ObtainUiMessagesContextClientRequest(extensionPoint.context()).send();
    }
}
