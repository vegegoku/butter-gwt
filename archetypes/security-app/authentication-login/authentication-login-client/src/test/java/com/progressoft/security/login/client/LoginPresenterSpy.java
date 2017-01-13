package com.progressoft.security.login.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.login.client.presenters.DefaultLoginPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class LoginPresenterSpy extends DefaultLoginPresenter{

    private AuthenticationContext context;
    private boolean loginRequestExecuted;

    @Override
    protected String getConcrete() {
        return DefaultLoginPresenter.class.getCanonicalName();
    }

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        super.onAuthenticationContextRecieved(context);
        this.context=context;
    }

    public FakeAuthenticationContext getContext() {
        return (FakeAuthenticationContext) context;
    }

    public boolean loginRequestExecuted() {
        return loginRequestExecuted;
    }


}
