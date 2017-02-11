package com.progressoft.security.uimessages.client.presenters;

import com.progressoft.security.uimessages.client.requests.ShowErrorMessageClientRequest;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;

class DefaultUiMessagesContext implements UiMessagesContext {
    @Override
    public void showError(String message, String details) {
        new ShowErrorMessageClientRequest(message, details).send();
    }
}
