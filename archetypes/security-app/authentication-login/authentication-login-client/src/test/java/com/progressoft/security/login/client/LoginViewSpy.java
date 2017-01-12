package com.progressoft.security.login.client;

import com.progressoft.security.login.client.views.DefaultLoginView;

public class LoginViewSpy extends DefaultLoginView {

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
