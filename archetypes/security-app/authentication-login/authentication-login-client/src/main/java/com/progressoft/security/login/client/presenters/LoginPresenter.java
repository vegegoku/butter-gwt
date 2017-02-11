package com.progressoft.security.login.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

public interface LoginPresenter extends Presentable{

    void onAuthenticationContextRecieved(AuthenticationContext context);

    void showLoginDialog(String defaultTenant);

    void showError(String message, String details);

    void onLoginSuccess(Principal principal);

    void onLayoutContextReceived(AuthenticationLayoutContext context);

    void onUiMessagesContextRecieved(UiMessagesContext uiMessagesContext);
}