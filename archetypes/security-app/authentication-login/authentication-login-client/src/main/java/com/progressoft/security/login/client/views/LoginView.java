package com.progressoft.security.login.client.views;

import com.progressoft.security.login.client.presenters.Conclusion;
import com.progressoft.security.login.client.presenters.LoginHandler;
import org.akab.engine.core.api.client.mvp.view.View;

public interface LoginView extends View{

    LoginView show(String defaultTenant);

    void hide();

    void addLoginHandler(LoginHandler loginHandler);

    void invalidateFields(Conclusion conclusion);

}