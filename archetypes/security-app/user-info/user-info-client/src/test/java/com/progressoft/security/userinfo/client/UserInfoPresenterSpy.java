package com.progressoft.security.userinfo.client;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.userinfo.client.presenters.DefaultUserInfoPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class UserInfoPresenterSpy extends DefaultUserInfoPresenter{

    private FakeAuthenticationCompletedContext fakeAuthenticationCompletedContext;
    private FakeAppLayoutContext fakeAppLayoutContext;

    @Override
    protected String getConcrete() {
        return DefaultUserInfoPresenter.class.getCanonicalName();
    }

    public FakeAuthenticationCompletedContext getAuthenticationCompletedContext() {
        return fakeAuthenticationCompletedContext;
    }

    @Override
    public void onAuthenticationCompletedContextReceived(AuthenticationCompletedContext context) {
        super.onAuthenticationCompletedContextReceived(context);
        this.fakeAuthenticationCompletedContext= (FakeAuthenticationCompletedContext) context;
    }

    @Override
    public void onAppLayoutContextRecieved(AppLayoutContext context) {
        super.onAppLayoutContextRecieved(context);
        this.fakeAppLayoutContext= (FakeAppLayoutContext) context;
    }

    public AppLayoutContext getAppLayoutContext() {
        return this.fakeAppLayoutContext;
    }
}
