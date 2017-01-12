package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.client.views.DefaultAuthenticationView;

public class AuthenticationViewSpy extends DefaultAuthenticationView {

    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void showErrorMessage(String message) {
        super.showErrorMessage(message);
        this.errorMessage = message;
    }
}
