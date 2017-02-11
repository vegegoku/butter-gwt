package com.progressoft.security.uimessages.client;

import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import com.progressoft.security.uimessages.shared.extension.UiMessagesExtensionPoint;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeUiMessagesContribution implements Contribution<UiMessagesExtensionPoint> {

    private UiMessagesContext uiMessagesContext;

    @Override
    public void contribute(UiMessagesExtensionPoint extensionPoint) {
        this.uiMessagesContext=extensionPoint.context();
    }

    public UiMessagesContext getUiMessagesContext() {
        return uiMessagesContext;
    }
}
