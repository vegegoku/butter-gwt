package com.progressoft.security.logout.client.presenters;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface LogoutPresenter extends Presentable{
    void onAppLayoutContextReceived(AppLayoutContext context);

    void onLoggedOut();

}