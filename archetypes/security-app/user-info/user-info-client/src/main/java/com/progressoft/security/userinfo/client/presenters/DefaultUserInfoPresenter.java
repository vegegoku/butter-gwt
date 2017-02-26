package com.progressoft.security.userinfo.client.presenters;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.userinfo.client.views.UserInfoCompositeView;
import com.progressoft.security.userinfo.client.views.UserInfoView;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;

@Presenter
public class DefaultUserInfoPresenter extends BaseClientPresenter<UserInfoCompositeView> implements UserInfoPresenter {

    private AuthenticationCompletedContext authenticationCompletedContext;
    private AppLayoutContext appLayoutContext;

    @Override
    public void onAuthenticationCompletedContextReceived(AuthenticationCompletedContext context) {
        this.authenticationCompletedContext=context;

    }

    private String getPrincipalDisplayName() {
        return this.authenticationCompletedContext.getPrincipal().getDisplayName();
    }

    @Override
    public void onAppLayoutContextRecieved(AppLayoutContext context) {
        this.appLayoutContext=context;
        view.setDisplayName(getPrincipalDisplayName());
        this.appLayoutContext.addHeaderItem(view.getView(UserInfoView.HEADER));
        this.appLayoutContext.addMenuItem(view.getView(UserInfoView.MENU), 0);
    }
}