package com.progressoft.security.login.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public interface LoginPresenter extends Presentable{

    void onAuthenticationContextRecieved(AuthenticationContext context);

    void showLoginDialog(String defaultTenant);
}