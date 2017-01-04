package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.client.views.DefaultAuthenticationView;

public class AuthenticationViewSpy extends DefaultAuthenticationView {

    private String welcomeMessage;

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        super.setWelcomeMessage(welcomeMessage);
        this.welcomeMessage=welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
