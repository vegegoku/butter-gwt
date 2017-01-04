package com.progressoft.security.login.client;

import com.progressoft.security.login.client.views.DefaultLoginView;

public class LoginViewSpy extends DefaultLoginView {

    private String description;

    @Override
    protected void setSupportDescription(String text) {
        super.setSupportDescription(text);
        this.description=text;
    }

    public String getDescription() {
        return description;
    }
}
