package com.progressoft.security.layout.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.layout.client.presenters.AuthenticationLayoutPresenter;
import com.progressoft.security.layout.client.presenters.DefaultAuthenticationLayoutPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class AuthenticationLayoutPresenterSpy extends DefaultAuthenticationLayoutPresenter {

    public AuthenticationCompletedContext authenticationCompletedContext;
    public boolean viewAddedToRoot;

    @Override
    protected String getConcrete() {
        return DefaultAuthenticationLayoutPresenter.class.getCanonicalName();
    }

    @Override
    public void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context) {
        this.authenticationCompletedContext=context;
        super.onAuthenticationCompletedContextRecieved(context);
    }

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint) {
        super.contributeToMainModule(mainExtensionPoint);
        this.viewAddedToRoot=true;
    }
}
