package com.progressoft.security.app.layout.client;

import com.progressoft.security.app.layout.client.presenters.DefaultAppLayoutPresenter;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;

public class AppLayoutPresenterSpy extends DefaultAppLayoutPresenter {

    public AuthenticationCompletedContext authenticationCompletedContext;
    public boolean layoutShown;

    @Override
    protected String getConcrete() {
        return DefaultAppLayoutPresenter.class.getCanonicalName();
    }

    @Override
    public void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context) {
        this.authenticationCompletedContext=context;
        super.onAuthenticationCompletedContextRecieved(context);
        layoutShown=true;
    }
}
