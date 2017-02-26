package com.progressoft.security.logout.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.logout.client.presenters.DefaultLogoutPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class LogoutPresenterSpy extends DefaultLogoutPresenter{

    public AppLayoutContext appLayoutContext;

    @Override
    protected String getConcrete() {
        return DefaultLogoutPresenter.class.getCanonicalName();
    }

    @Override
    public void onAppLayoutContextReceived(AppLayoutContext context) {
        super.onAppLayoutContextReceived(context);
        this.appLayoutContext=context;
    }
}
