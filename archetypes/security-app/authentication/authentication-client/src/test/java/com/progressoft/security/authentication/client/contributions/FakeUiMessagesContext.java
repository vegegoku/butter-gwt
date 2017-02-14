package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;

public class FakeUiMessagesContext implements UiMessagesContext {

    private String message;
    private String details;

    @Override
    public void showError(String message, String details) {
        this.message=message;
        this.details=details;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
