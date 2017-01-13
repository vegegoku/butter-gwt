package com.progressoft.security.login.client.presenters;

import com.progressoft.security.login.shared.extension.LoginCredentials;

@FunctionalInterface
public interface LoginHandler {
    void handle(LoginCredentials loginCredentials);
}
