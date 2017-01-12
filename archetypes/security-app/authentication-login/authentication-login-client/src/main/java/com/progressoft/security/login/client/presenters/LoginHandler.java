package com.progressoft.security.login.client.presenters;

@FunctionalInterface
public interface LoginHandler {
    void handle(LoginCredentials loginCredentials);
}
