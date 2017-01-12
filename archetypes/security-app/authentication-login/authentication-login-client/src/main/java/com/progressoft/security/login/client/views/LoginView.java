package com.progressoft.security.login.client.views;

import com.progressoft.security.login.client.presenters.LoginCredentials;
import com.progressoft.security.login.client.presenters.LoginHandler;
import org.akab.engine.core.api.client.mvp.view.View;

public interface LoginView extends View{
    void show(String defaultTenant);

    void addLoginHandler(LoginHandler loginHandler);

    void invalidateUserName(String errorMessage);
    void invalidatePassword(String errorMessage);
    void invalidateTenant(String errorMessage);
}