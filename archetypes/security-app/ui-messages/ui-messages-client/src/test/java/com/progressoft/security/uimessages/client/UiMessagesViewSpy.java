package com.progressoft.security.uimessages.client;

import com.progressoft.security.uimessages.client.views.DefaultUiMessagesView;

public class UiMessagesViewSpy extends DefaultUiMessagesView {

    public boolean errorMessageVisible;
    public String errorMessage;
    public String errorDescription;

    @Override
    public void showError(String message, String details) {
        super.showError(message, details);
        this.errorMessage=message;
        this.errorDescription=details;
        this.errorMessageVisible=true;
    }
}
