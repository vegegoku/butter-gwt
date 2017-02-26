package com.progressoft.security.userinfo.client.presenters;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface UserInfoPresenter extends Presentable{
    void onAuthenticationCompletedContextReceived(AuthenticationCompletedContext context);

    void onAppLayoutContextRecieved(AppLayoutContext context);
}