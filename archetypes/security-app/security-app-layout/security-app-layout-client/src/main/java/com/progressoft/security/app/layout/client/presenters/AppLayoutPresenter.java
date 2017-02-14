package com.progressoft.security.app.layout.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface AppLayoutPresenter extends Presentable{
    void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context);
    void onMainContextRecieved(MainContext context);
}