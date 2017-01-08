package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.client.views.DefaultAuthenticationView;

public class AuthenticationViewSpy extends DefaultAuthenticationView {

    private String welcomeMessage;
    private String failedAuthenticationCompletionMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getFailedAuthenticationCompletionMessage() {
        return failedAuthenticationCompletionMessage;
    }

    @Override
    public void showErrorMessage(String message) {
        super.showErrorMessage(message);
        this.failedAuthenticationCompletionMessage=message;
    }
}
